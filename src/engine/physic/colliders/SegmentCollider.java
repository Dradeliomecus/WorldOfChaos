package engine.physic.colliders;

import engine.math.Matrix2l;
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
	public @Nullable CollisionInfo intersect(final @NotNull Collider collider, final @NotNull Position prevPos) {
		// We have a segment AB moving through a vector AC.
		final Position AB = this.p2.sub(this.p1);
		final Position AC = this.p1.sub(prevPos);

		// Parallelogram made by AB and AC as a matrix.
		final Matrix2l pMatrix = new Matrix2l();
		pMatrix.setCol(0, AB);
		pMatrix.setCol(1, AC);
		final long pD = pMatrix.det();

		if(collider instanceof PointCollider) {
			// To check if the point is inside the parallelogram made by the vectors AB and AC,
			// We recalculate the coordinates of AP as AP = m*AB + n*AC
			// If x and y are between 0 and 1n the P is inside the parallelogram.
			final PointCollider pointCollider = (PointCollider) collider;
			final Position AP = pointCollider.getPosition().sub(prevPos);

			if(pD == 0) { // Line moves along its axis, not making a parallelogram.
				// TODO.
			} else { // Moving line makes a parallelogram.
				final double m = (AP.getX()-AC.getY() * AP.getY()- AC.getX()) / (double) pD;
				final double n = (AP.getX()-AB.getY() * AP.getY()- AB.getX()) / (double) pD;

				if(m <= 0 || m >= 1 || n <= 0 || n >= 1) {
					return null;
				}

				final Position collPos = prevPos.addXY(Math.round(AC.getX() * m), Math.round(AC.getY() * m));
				return new CollisionInfo(collPos, pointCollider.getPosition(), AB.asVector2f());
			}
		} else if(collider instanceof SegmentCollider) {
			// TODO.
		} else if(collider instanceof AABBCollider) {
			// TODO.
		} else if(collider instanceof CircleCollider) {
			// TODO.
		}

		System.err.println("Error: Collision between SegmentCollider and " + collider + " isn't implemented yet.");
		System.err.println("Or the collision has been mishandled.");
		System.err.println("\t- prevPos = " + prevPos);
		System.err.println("\t- line = [ " + this.p1 + " ; " + this.p2 + " ]");
		return null;
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
	final public @NotNull Position getPosition() {
		return this.getP1();
	}

	@Contract(pure = true)
	@Override
	public int area() {
		return 0;
	}

}