package engine.physic.colliders;

import engine.math.Matrix2l;
import engine.math.Vector2f;
import engine.util.Position;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PointCollider extends Collider {

	/**
	 * Point's position.
	 */
	private @NotNull Position position;

	/**
	 * Creates a new PointCollider instance.
	 *
	 * @param position Position to set
	 */
	public PointCollider(final @NotNull Position position) {
		this.position = position;
	}

	/**
	 * Creates a new PointCollider instance.
	 *
	 * @param x X position to set
	 * @param y Y position to set
	 */
	public PointCollider(final long x, final long y) {
		this.position = new Position(x, y);
	}

	@Override
	public @NotNull String toString() {
		return super.toString() + " (" + this.getPosition() + ")";
	}

	@Contract(pure = true)
	@Override
	public @Nullable CollisionInfo intersect(final @NotNull Collider collider, final @NotNull Position prevPos) {
		final SegmentCollider trajectory = new SegmentCollider(prevPos, this.getPosition());

		if(collider instanceof PointCollider) {
			if(!trajectory.containsOrOn(collider.getPosition()) || prevPos.equals(collider.getPosition())) {
				return null;
			}

			final Vector2f direction = collider.getPosition().sub(prevPos).asVector2f(); // Perpendicular to the tangent.
			final Vector2f tangent = new Vector2f(direction.getY(), -direction.getX());
			return new CollisionInfo(collider.getPosition(), collider.getPosition(), tangent);
		} else if(collider instanceof SegmentCollider) {
			// For the math, look at: https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line
			final SegmentCollider segmentCollider = (SegmentCollider) collider;

			final long x1 = prevPos.getX();
			final long x2 = this.position.getX();
			final long x3 = segmentCollider.getP1().getX();
			final long x4 = segmentCollider.getP2().getX();
			final long y1 = prevPos.getY();
			final long y2 = this.position.getY();
			final long y3 = segmentCollider.getP1().getY();
			final long y4 = segmentCollider.getP2().getY();

			final long D = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);

			if(D == 0) { // trajectory and segmentCollider are parallel.
				// Need to check if points are aligned or not. For that, use: https://www.urbanpro.com/gre/how-to-determine-if-points-are-collinear#:~:text=Three%20or%20more%20points%20are,and%20C%20are%20collinear%20points.
				final Matrix2l matrix = new Matrix2l();
				matrix.setCol(0, trajectory.getP1().sub(trajectory.getP2()));
				matrix.setCol(1, trajectory.getP2().sub(segmentCollider.getP1()));

				if(matrix.det() < 1) { // The 3 points are aligned, thus the 2 segments might collide.
					// Parametric equation: x = Ax + at ; y = Ay + bt
					final long a = segmentCollider.getP2().getX() - segmentCollider.getP1().getX();
					final long b = segmentCollider.getP2().getY() - segmentCollider.getP1().getY();

					// segmentCollider has t=0 and t=1. Need to check trajectory to see if they intersect.
					final double t1 = (double)(trajectory.getP1().getX() + trajectory.getP1().getY() - segmentCollider.getP1().getX() - segmentCollider.getP1().getY()) / (a+b);
					final double t2 = (double)(trajectory.getP2().getX() + trajectory.getP2().getY() - segmentCollider.getP1().getX() - segmentCollider.getP1().getY()) / (a+b);

					if(t1 > 0 && t1 < 1) { // (this) started on the segmentCollider.
						return new CollisionInfo(prevPos, prevPos, null);
					} else if(t1 < 0 && t2 > 0) {
						return new CollisionInfo(segmentCollider.getP1(), segmentCollider.getP1(), new Vector2f(b, -a));
					} else if(t1 >1 && t2 < 1) {
						return new CollisionInfo(segmentCollider.getP2(), segmentCollider.getP2(), new Vector2f(b, -a));
					}
				}
			} else { // trajectory and segmentCollider are not parallel.
				final double X = (x1*y2 - x2*y1) * (x3-x4) - (x1-x2) * (x3*y4 - y3*x4);
				final double Y = (x1*y2 - x2*y1) * (y3-y4) - (y1-y2) * (x3*y4 - y3*x4);

				final Position collisionPoint = new Position(Math.round(X/D), Math.round(Y/D));
				if(!segmentCollider.contains(collisionPoint) || !trajectory.on(collisionPoint)) {
					return null;
				}

				return new CollisionInfo(collisionPoint, collisionPoint, segmentCollider.getP1().sub(segmentCollider.getP2()).asVector2f());
			}
		} else if(collider instanceof AABBCollider) {
			// For the math, look at: https://en.wikipedia.org/wiki/Liang%E2%80%93Barsky_algorithm
			final AABBCollider aabbCollider = (AABBCollider) collider;
			
			if(aabbCollider.contains(prevPos)) {
				return new CollisionInfo(prevPos, prevPos, null);
			}

			final long[] p = new long[] {
				prevPos.getX() - this.position.getX(),
				this.position.getX() - prevPos.getX(),
				prevPos.getY() - this.position.getY(),
				this.position.getY() - prevPos.getY()
			};
			final long[] q = new long[] {
				prevPos.getX() - aabbCollider.getMinExtents().getX(),
				aabbCollider.getMaxExtents().getX() - prevPos.getX(),
				prevPos.getY() - aabbCollider.getMinExtents().getY(),
				aabbCollider.getMaxExtents().getY() - prevPos.getY()
			};

			for(int i=0; i<4; i++) {
				if(p[i] == 0 && q[i] <= 0) {
					return null;
				}
			}

			// Values of t in line parametric equations where u is first collision point, v the second one.
			double u = 0;
			double v = 1;

			if(p[0] != 0) {
				final double r0 = (double) q[0] / p[0];
				final double r1 = (double) q[1] / p[1];

				u = Math.max(u, p[0]<0 ? r0 : r1);
				v = Math.min(v, p[0]<0 ? r1 : r0);
			}
			if(p[2] != 0) {
				final double r2 = (double) q[2] / p[2];
				final double r3 = (double) q[3] / p[3];

				u = Math.max(u, p[2]<0 ? r2 : r3);
				v = Math.min(v, p[2]<0 ? r3 : r2);
			}

			if(u > v) { // No intersection.
				return null;
			}

			final long x0 = prevPos.getX() + Math.round(p[1] * u);
			final long y0 = prevPos.getY() + Math.round(p[3] * u);

			final Position collisionPoint = new Position(x0, y0);
			final Vector2f tangent;

			// TODO: There might be a better way to compute the tangent.
			if((collisionPoint.getX() == aabbCollider.getMinExtents().getX() || collisionPoint.getX() == aabbCollider.getMaxExtents().getX()) &&
			   (collisionPoint.getY() != aabbCollider.getMinExtents().getY() && collisionPoint.getY() != aabbCollider.getMaxExtents().getY())) {
				tangent = new Vector2f(0, 1);
			} else if((collisionPoint.getY() == aabbCollider.getMinExtents().getY() || collisionPoint.getY() == aabbCollider.getMaxExtents().getY()) &&
					  (collisionPoint.getX() != aabbCollider.getMinExtents().getX() && collisionPoint.getX() != aabbCollider.getMaxExtents().getX())) {
				tangent = new Vector2f(1, 0);
			} else if(collisionPoint.distanceToXY(aabbCollider.getMinExtents()) < 1 || collisionPoint.distanceToXY(aabbCollider.getMaxExtents()) < 1) {
				tangent = new Vector2f(1, -1);
			} else {
				tangent = new Vector2f(1, 1);
			}

			return new CollisionInfo(collisionPoint, collisionPoint, tangent);
		} else if(collider instanceof CircleCollider) {
			// For the math, look at: https://en.wikipedia.org/wiki/Intersection_(Euclidean_geometry)#A_line_and_a_circle
			// Shift everything so that center of the circle is 0, and the line becomes ax + by + c = 0
			final CircleCollider circleCollider = (CircleCollider) collider;

			final long a = trajectory.getP1().getY() - trajectory.getP2().getY();
			final long b = trajectory.getP2().getX() - trajectory.getP1().getX();
			final long c = (trajectory.getP1().getX() - circleCollider.getCenter().getX()) * (trajectory.getP2().getY() - circleCollider.getCenter().getY())
						 - (trajectory.getP2().getX() - circleCollider.getCenter().getX()) * (trajectory.getP1().getY() - circleCollider.getCenter().getY());
			final long r = circleCollider.getRadius();

			final long denominator = a*a + b*b;
			long D = r*r * denominator - c*c;

			if(D <= 0) { // No intersection or intersection in only one point.
				return null;
			}
			D = Math.round(Math.sqrt(D));

			// TODO: From this point, isn't there a better way?
			final Position collisionPoint; // Need to determine which of the 2 possible collision points are the first one.
			final Position collision1 = new Position((a*c - b*D)/denominator, (b*c + a*D)/denominator);
			final Position collision2 = new Position((a*c + b*D)/denominator, (b*c - a*D)/denominator);
			final boolean coll1OnTraj = trajectory.on(collision1);
			final boolean coll2OnTraj = trajectory.on(collision2);

			if(!coll1OnTraj && !coll2OnTraj) {
				return null;
			} else if(coll1OnTraj && !coll2OnTraj) {
				collisionPoint = collision1;
			} else if(!coll1OnTraj) {
				collisionPoint = collision2;
			} else {
				final int dist1 = collision1.distanceToXY(this.position);
				final int dist2 = collision2.distanceToXY(this.position);

				collisionPoint = dist1 < dist2 ? collision1 : collision2;
			}

			collisionPoint.addition(circleCollider.getCenter()); // Because up until here, we worked with everything translated by the circle center.
			final Vector2f direction = collisionPoint.sub(circleCollider.getCenter()).asVector2f(); // Perpendicular to the tangent.
			final Vector2f tangent = new Vector2f(direction.getY(), -direction.getX());

			return new CollisionInfo(collisionPoint, collisionPoint, tangent);
		}

		System.err.println("Error: Collision between PointCollider and " + collider + " isn't implemented yet.");
		System.err.println("Or the collision has been mishandled.");
		System.err.println("\t- prevPos = " + prevPos);
		System.err.println("\t- pos = " + this.position);
		return null;
	}

	@Override
	final public @NotNull Position getPosition() {
		return new Position(this.position);
	}

	@Contract(pure = true)
	@Override
	public boolean contains(final @NotNull Position point) {
		return false;
	}

	@Contract(pure = true)
	@Override
	public boolean on(final @NotNull Position point) {
		return this.position.getX() == point.getX() && this.position.getY() == point.getY();
	}

	@Contract(pure = true)
	@Override
	public boolean containsOrOn(final @NotNull Position point) {
		return this.on(point);
	}

	@Contract(pure = true)
	@Override
	public int area() {
		return 0;
	}

	/**
	 * Sets the PointCollider position.
	 *
	 * @param position Position to set
	 */
	public void setPosition(final @NotNull Position position) {
		this.position = position;
	}

}