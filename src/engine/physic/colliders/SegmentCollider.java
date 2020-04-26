package engine.physic.colliders;

import engine.util.Position;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SegmentCollider extends Collider {

	/**
	 * Segment's starting point.
	 */
	private @NotNull Position p1;

	/**
	 * Segment's ending point.
	 */
	private @NotNull Position p2;

	/**
	 * Creates a new SegmentCollider instance.
	 *
	 * @param p1 Starting point to set
	 * @param p2 Ending point to set
	 */
	public SegmentCollider(final @NotNull Position p1, final @NotNull Position p2) {
		if(p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
			System.err.println("Error: P1 and P2 are the same while initializing SegmentCollider.");
			System.err.println("Future errors are bound to happen.");
			new Exception().printStackTrace();
		}

		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public @NotNull String toString() {
		return super.toString() + " (" + this.getP1() + " ; " + this.getP2() + ")";
	}

	@Contract(pure = true)
	@Override
	public @Nullable CollisionInfo intersect(final @NotNull Collider collider) {
		// Equation for this: ax + by + c = 0
		final long a = this.p1.getY() - this.p2.getY();
		final long b = this.p2.getX() - this.p1.getX();
		final long c = this.p1.getX()*this.p2.getY() - this.p2.getX()*this.p1.getY();
		final long xMin = Math.min(this.getP1().getX(), this.getP2().getX());
		final long xMax = Math.max(this.getP1().getX(), this.getP2().getX());
		final long yMin = Math.min(this.getP1().getY(), this.getP2().getY());
		final long yMax = Math.max(this.getP1().getY(), this.getP2().getY());
		final boolean leftToRight = this.getP1().getX() < this.getP2().getX();

		if(collider instanceof PointCollider) {
			return collider.intersect(this);
		} else if(collider instanceof SegmentCollider) {
			final SegmentCollider line = (SegmentCollider) collider;
			// Line equation : ax + by + c = 0
			final long a2 = line.p1.getY() - line.p2.getY();
			final long b2 = line.p2.getX() - line.p1.getX();
			final long c2 = line.p1.getX()*line.p2.getY() - line.p2.getX()*line.p1.getY();
			final double det = a*b2 - a2*b;

			if(det == 0) { // Both lines are parallel.
				final double factor = a == 0 ? (double)b/(double)b2 : (double)a/(double)a2;
				if(Math.abs(c2 * factor - c) >= 1) return null;

				if(a != 0) {
					final long yMin2 = Math.min(line.getP1().getY(), line.getP2().getY());
					final long yMax2 = Math.max(line.getP1().getY(), line.getP2().getY());

					if(yMin >= yMax2 || yMax <= yMin2) return null;
					return new CollisionInfo(new SegmentCollider(new Position(this.getP1().getX(), Math.max(yMin, yMin2)), new Position(this.getP1().getX(), Math.min(yMax, yMax2))), null);
				} else {
					final long xMin2 = Math.min(line.getP1().getX(), line.getP2().getX());
					final long xMax2 = Math.max(line.getP1().getX(), line.getP2().getX());

					if(xMin >= xMax2 || xMax <= xMin2) return null;

					final long x1 = Math.max(xMin, xMin2);
					final long y1 = -(a*x1 + c)/b;
					final long x2 = Math.min(xMax, xMax2);
					final long y2 = -(a*x2 + c)/b;
					return new CollisionInfo(new SegmentCollider(new Position(x1, y1), new Position(x2, y2)), null);
				}
			} else {
				final int x = (int) Math.round((double)(b*c2 - b2*c)/det);
				final int y = (int) Math.round((double)(a2*c - a*c2)/det);
				final Position result = new Position(x, y);

				if(this.contains(result) && line.contains(result)) {
					return new CollisionInfo(new PointCollider(result), result);
				} else {
					return null;
				}
			}
		} else if(collider instanceof AABBCollider) {
			final Position minExtents = ((AABBCollider) collider).getPosition();
			final Position maxExtents = ((AABBCollider) collider).getMaxExtents();

			if(a == 0) { // Then we have y = -c/b
				final long y = -Math.round((double) c / b);

				if(y <= minExtents.getY() || y >= maxExtents.getY()) {
					return null;
				}

				final long x1 = Math.max(xMin, minExtents.getX());
				final long x2 = Math.min(xMax, maxExtents.getX());

				if(x1 == x2) {
					return null;
				}

				final Position pointCollider = leftToRight ? new Position(x1, y) : new Position(x2, y);
				return new CollisionInfo(new SegmentCollider(new Position(x1, y), new Position(x2, y)), pointCollider);
			} else if(b == 0) { // Then we have x = -c/a
				final long x = -Math.round((double) c / a);

				if(x <= minExtents.getX() || x >= maxExtents.getX()) {
					return null;
				}

				final long y1 = Math.max(yMin, minExtents.getY());
				final long y2 = Math.min(yMax, maxExtents.getY());

				if(y1 == y2) {
					return null;
				}

				final Position pointCollider = leftToRight ? new Position(x, y1) : new Position(x, y2);
				return new CollisionInfo(new SegmentCollider(new Position(x, y1), new Position(x, y2)), pointCollider);
			} else {
				long x1 = Math.min(Math.max(-Math.round((double)(b * minExtents.getY() + c) / a), minExtents.getX()), maxExtents.getX());
				long x2 = Math.max(Math.min(-Math.round((double)(b * maxExtents.getY() + c) / a), maxExtents.getX()), minExtents.getX());

				if(x1 > x2) { // We want x1 < x2
					final long x3 = x1;
					x1 = x2;
					x2 = x3;
				}

				if(this.getP1().getX() > x1 && this.getP2().getX() > x1) {
					x1 = xMin;
				}
				if(this.getP1().getX() < x2 && this.getP2().getX() < x2) {
					x2 = xMax;
				}

				final long y1 = -Math.round((double)(a*x1 + c) / b);
				final long y2 = -Math.round((double)(a*x2 + c) / b);

				if(x1 > x2) {
					return null;
				} else if(x1 == x2) { // The segment intersects with a corner.
					return null;
				} else {
					final Position collisionPoint = leftToRight ? new Position(x1, y1) : new Position(x2, y2);
					return new CollisionInfo(new SegmentCollider(new Position(x1, y1), new Position(x2, y2)), collisionPoint);
				}
			}
		} else if(collider instanceof CircleCollider) {
			// Circle equation : (x-p)² + (y-q)² = r²
			final long p = ((CircleCollider) collider).getCenter().getX();
			final long q = ((CircleCollider) collider).getCenter().getY();
			final long r = ((CircleCollider) collider).getRadius();

			if(a == 0) { // Then we have y = -b/a
				final long y = -Math.round((double) c / b);

				if(y <= q-r || y >= q+r) { // No collision.
					return null;
				}

				final long x1 = Math.max(p-r, leftToRight ? this.getP1().getX() : this.getP2().getX());
				final long x2 = Math.min(p+r, leftToRight ? this.getP2().getX() : this.getP1().getX());

				if(x1 >= x2) { // No collision with the circle (but if the segment was infinite, there would be).
					return null;
				}

				final Position collisionPoint = leftToRight ? new Position(x1, y) : new Position(x2, y);
				return new CollisionInfo(new SegmentCollider(new Position(x1, y), new Position(x2, y)), collisionPoint);
			} else if(b == 0) { // Then we have x = -c/a
				final long x = -Math.round((double) c / a);

				if(x <= p-r || x >= p+r) { // No collision.
					return null;
				}

				final long y1 = Math.max(q-r, Math.min(this.getP1().getY(), this.getP2().getY()));
				final long y2 = Math.min(q+r, Math.max(this.getP1().getY(), this.getP2().getY()));

				if(y1 >= y2) { // No collision with the circle (but if the segment was infinite, there would be).
					return null;
				}

				final Position collisionPoint = this.getP1().getY() < this.getP2().getY() ? new Position(x, y1) : new Position(x, y2);
				return new CollisionInfo(new SegmentCollider(new Position(x, y1), new Position(x, y2)), collisionPoint);
			} else {
				// (x-p)² + ((ax+c)/b + q)² = r²
				// Then, we have an equation with Ax² + Bx + C = 0.
				final double A = 1 + (double)(a*a) / (double)(b*b);
				final double B = (double)(2*a*c)/(double)(b*b) + (double)(2*a*q)/(double)(b) - 2*p;
				final double C = p*p + q*q - r*r + (double)(2*c*q)/(double)(b) + (double)(c*c)/(double)(b*b);
				final double d = B*B - 4*A*C;

				if(Math.round(d) <= 0) { // No collision even if the segment was infinite.
					return null;
				}

				final double D = Math.sqrt(d);

				long x1 = Math.round((-B - D * (A>0 ? 1 : -1)) / (2*A));
				long x2 = Math.round((-B + D * (A>0 ? 1 : -1)) / (2*A));

				if(x1 < xMin) {
					x1 = xMin;
				}
				if(x2 > xMax) {
					x2 = xMax;
				}

				if(x2 <= x1) { // No collision (but if the segment was infinite, there would be).
					return null;
				}

				final long y1 = -Math.round((double)(a*x1 + c) / b);
				final long y2 = -Math.round((double)(a*x2 + c) / b);

				final Position collisionPoint = leftToRight ? new Position(x1, y1) : new Position(x2, y2);
				return new CollisionInfo(new SegmentCollider(new Position(x1, y1), new Position(x2, y2)), collisionPoint);
			}
		} else {
			System.err.println("Error: Collision between SegmentCollider and " + collider + " isn't implemented yet.");
			new Exception().printStackTrace();
			return null;
		}
	}

	@Contract(pure = true)
	@Override
	public boolean contains(final @NotNull Position point) {
		return this.on(point) && !point.equalsXY(this.p1) && !point.equalsXY(this.p2);
	}

	@Contract(pure = true)
	@Override
	public boolean on(final @NotNull Position point) {
		// Line equation : ax + by + c = 0
		final long a = this.p1.getY() - this.p2.getY();
		final long b = this.p2.getX() - this.p1.getX();
		final long c = this.p1.getX()*this.p2.getY() - this.p2.getX()*this.p1.getY();

		if(Math.abs(a*point.getX() + b*point.getY() + c) > 1) return false;

		if(point.getX() < Math.min(this.p1.getX(), this.p2.getX())) return false;
		if(point.getX() > Math.max(this.p1.getX(), this.p2.getX())) return false;
		if(point.getY() < Math.min(this.p1.getY(), this.p2.getY())) return false;
		if(point.getY() > Math.max(this.p1.getY(), this.p2.getY())) return false;

		return true;
	}

	@Contract(pure = true)
	@Override
	public boolean containsOrOn(final @NotNull Position point) {
		return this.on(point);
	}

	/**
	 * Returns a copy of the Segment's starting point (and not the pointer).
	 *
	 * @return new Position(SegmentCollider.p1)
	 */
	@Contract(pure = true)
	final public @NotNull Position getP1() {
		return new Position(this.p1);
	}

	/**
	 * Returns a copy of the Segment's ending point (and not the pointer).
	 *
	 * @return new Position(SegmentCollider.p2)
	 */
	@Contract(pure = true)
	final public @NotNull Position getP2() {
		return new Position(this.p2);
	}

	@Contract(pure = true)
	@Override
	public int area() {
		return 0;
	}

}