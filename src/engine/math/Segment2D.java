package engine.math;

import engine.physic.OlderAABBCollider;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import support.ArrayList;

public class Segment2D {

	/**
	 * Starting point of the segment.
	 */
	private @NotNull Vector2f p1;

	/**
	 * Ending point of the segment.
	 */
	private @NotNull Vector2f p2;

	/**
	 * Creates a new Segment2D instance.
	 * Points sent are a copy and not the pointers.
	 *
	 * @param p1 Starting point to set
	 * @param p2 Ending point to set
	 */
	public Segment2D(final @NotNull Vector2f p1, final @NotNull Vector2f p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public @NotNull String toString() {
		return "Segment2D [ " + this.getP1() + " ; " + this.getP2() +" ]";
	}

	/**
	 * Returns the point where (this) intersect with the given line.
	 * Returns null if there is no intersection or if both lines are parallel.
	 *
	 * @param line Segment to check with
	 * @return new Vector2f (or null)
	 */
	final public @Nullable Vector2f intersect(final @NotNull Segment2D line) {
		final float x;
		final float y;

		if(this.getP2().getX() == this.getP1().getX() && line.getP2().getX() == line.getP1().getX()) {
			return null; // Both lines are parallel (vertical).
		} else if(this.getP2().getX() == this.getP1().getX()) {
			final float a2 = (line.getP2().getY() - line.getP1().getY()) / (line.getP2().getX() - line.getP1().getX());
			final float b2 = line.getP1().getY() - a2 * line.getP1().getX();
			x = this.getP2().getX();
			y = a2*x + b2;
		} else if(line.getP2().getX() == line.getP1().getX()) {
			final float a1 = (this.getP2().getY() - this.getP1().getY()) / (this.getP2().getX() - this.getP1().getX());
			final float b1 = this.getP1().getY() - a1 * this.getP1().getX();
			x = line.getP2().getX();
			y = a1*x + b1;
		} else {
			final float a1 = (this.getP2().getY() - this.getP1().getY()) / (this.getP2().getX() - this.getP1().getX());
			final float a2 = (line.getP2().getY() - line.getP1().getY()) / (line.getP2().getX() - line.getP1().getX());
			if(a1 == a2) return null; // Parallel so no intersection or same line.

			final float b1 = this.getP1().getY() - a1 * this.getP1().getX();
			final float b2 = line.getP1().getY() - a2 * line.getP1().getX();
			x = (b2-b1)/(a1-a2);
			y = a1*x + b1;
		}

		if(x > this.getP1().getX() && x > this.getP2().getX()) return null;
		if(x < this.getP1().getX() && x < this.getP2().getX()) return null;
		if(x > line.getP1().getX() && x > line.getP2().getX()) return null;
		if(x < line.getP1().getX() && x < line.getP2().getX()) return null;
		if(y > this.getP1().getY() && y > this.getP2().getY()) return null;
		if(y < this.getP1().getY() && y < this.getP2().getY()) return null;
		if(y > line.getP1().getY() && y > line.getP2().getY()) return null;
		if(y < line.getP1().getY() && y < line.getP2().getY()) return null;

		return new Vector2f(x, y);
	}

	/**
	 * Returns all points where (this) intersect with the given AABBCollider (everything in openGL units).
	 * Returns an empty array if there is no intersection.
	 *
	 * @param collider Square to check with
	 * @return new ArrayList<Vector2f>
	 */
	final public @NotNull ArrayList<Vector2f> intersect(final @NotNull OlderAABBCollider collider) {
		final ArrayList<Vector2f> intersections = new ArrayList<>();

		final Segment2D[] segments = {
			new Segment2D(collider.getPosition(), collider.getPosition().add(collider.getWidth(), 0)),
			new Segment2D(collider.getPosition().add(collider.getWidth(), 0), collider.getPosition().add(collider.getWidth(), collider.getHeight())),
			new Segment2D(collider.getPosition().add(collider.getWidth(), collider.getHeight()), collider.getPosition().add(0, collider.getHeight())),
			new Segment2D(collider.getPosition().add(0, collider.getHeight()), collider.getPosition())
		};

		for(final Segment2D segment : segments) {
			final Vector2f intersection = this.intersect(segment);
			if(intersection == null) continue;

			boolean alreadyThere = false;
			for(final Vector2f intersec : intersections) {
				if(intersec.equals(intersection)) {
					alreadyThere = true;
					break;
				}
			}

			if(!alreadyThere) {
				intersections.add(intersection);
			}
		}

		return intersections;
	}

	/**
	 * Returns the Segment's starting point.
	 *
	 * @return Segment2D.p1
	 */
	@Contract(pure = true)
	final public @NotNull Vector2f getP1() {
		return this.p1;
	}

	/**
	 * Returns the Segment's ending point.
	 *
	 * @return Segment2D.p1
	 */
	@Contract(pure = true)
	final public @NotNull Vector2f getP2() {
		return this.p2;
	}

	/**
	 * Returns the Segment2D as a Vector2f (meaning p1 ==> (0, 0)).
	 *
	 * @return new Vector2f
	 */
	final public @NotNull Vector2f asVector2f() {
		return this.getP2().sub(this.getP1());
	}

	/**
	 * Sets the Segment's starting point (sends a copy and not the pointer).
	 *
	 * @param p1 Point to set
	 */
	final public void setP1(final @NotNull Vector2f p1) {
		this.p1 = new Vector2f(p1);
	}

	/**
	 * Sets the Segment's ending point (sends a copy and not the pointer).
	 *
	 * @param p2 Point to set
	 */
	final public void setP2(final @NotNull Vector2f p2) {
		this.p2 = new Vector2f(p2);
	}

	/**
	 * Sets the Segment's coordinates (sends a copy and not the pointer).
	 *
	 * @param p1 Starting point to set
	 * @param p2 Ending point to set
	 */
	final public void set(final @NotNull Vector2f p1, final @NotNull Vector2f p2) {
		this.setP1(p1);
		this.setP2(p2);
	}

}