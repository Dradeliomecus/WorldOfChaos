package engine.physic.colliders;

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
	 * @param position Position to set (sends a copy and not the pointer)
	 */
	public PointCollider(final @NotNull Position position) {
		this.position = new Position(position);
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
			if(trajectory.containsOrOn(collider.getPosition()) && !prevPos.equals(collider.getPosition())) {
				final Vector2f direction = this.position.sub(prevPos).asVector2f();
				final Vector2f tangent = new Vector2f(direction.getY(), -direction.getX());
				return new CollisionInfo(collider.getPosition(), collider.getPosition(), tangent);
			}
		} else if(collider instanceof SegmentCollider) {
			// For the math, look at: https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection
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
				// TODO.
			} else {
				final double X = (x1*y2 - x2*y1) * (x3-x4) - (x1-x2) * (x3*y4 - y3*x4);
				final double Y = (x1*y2 - x2*y1) * (y3-y4) - (y1-y2) * (x3*y4 - y3*x4);

				final Position collisionPoint = new Position(Math.round(X/D), Math.round(Y/D));
				if(!segmentCollider.contains(collisionPoint) || !trajectory.on(collisionPoint)) {
					return null;
				}

				return new CollisionInfo(collisionPoint, collisionPoint, segmentCollider.getP1().sub(segmentCollider.getP2()).asVector2f());
			}
		} else if(collider instanceof AABBCollider) {
			// TODO.
		} else if(collider instanceof CircleCollider) {
			// TODO.
		} else {
			System.err.println("Error: Collision between PointCollider and " + collider + " isn't implemented yet.");
			return null;
		}

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

}