package engine.oldPhysic.colliders;

import engine.math.Vector2f;
import engine.oldPhysic.OldIntersectData;

final public class OldCircle extends OldCollider {

	/**
	 * Circle's center point.
	 */
	private Vector2f center;

	/**
	 * Circle's radius.
	 */
	private float radius;

	/**
	 * Creates a new Circle instance.
	 *
	 * @param center Center point to set
	 * @param radius Radius to set
	 */
	public OldCircle(final Vector2f center, final float radius) {
		super();
		this.setCenter(center);
		this.setRadius(radius);
	}

	/**
	 * Creates a new copy of a Circle.
	 *
	 * @param other Circle to copy
	 */
	public OldCircle(final OldCircle other) {
		this(other.getCenter(), other.getRadius());
	}

	@Override
	public void transform(final Vector2f translation) {
		this.center.addition(translation);
	}

	/**
	 * Returns an IntersectData between the Circle and another Circle.
	 *
	 * @param circle Circle to check with
	 * @return new IntersectData
	 */
	final public OldIntersectData intersectCircle(final OldCircle circle) {
		final float radiusDistance = this.getRadius() + circle.getRadius();
		final float centerDistance = (circle.getCenter().sub(this.getCenter())).length();
		final float distance = centerDistance - radiusDistance;

		return new OldIntersectData(distance < 0, distance);
	}

	@Override
	final public Vector2f getCenter() {
		return new Vector2f(this.center);
	}

	/**
	 * Returns the Circle's radius.
	 *
	 * @return Circle.radius
	 */
	final public float getRadius() {
		return this.radius;
	}

	/**
	 * Sets the Circle's center point.
	 *
	 * @param center Center point to set
	 */
	private void setCenter(final Vector2f center) {
		this.center = center;
	}

	/**
	 * Sets the Circle's radius.
	 *
	 * @param radius Radius to set
	 */
	private void setRadius(final float radius) {
		this.radius = radius;
	}

}