package engine.oldPhysic.colliders;

import engine.math.Vector2f;
import engine.oldPhysic.OldIntersectData;

final public class OldAABB extends OldCollider {

	/**
	 * Smallest AABB's point.
	 */
	private Vector2f minExtents;

	/**
	 * Biggest AABB's point.
	 */
	private Vector2f maxExtents;

	/**
	 * Creates a new AABB (Axis-Aligned Bounding Box) instance.
	 *
	 * @param minExtents Min extents to set
	 * @param maxExtents Max extents to set
	 */
	public OldAABB(final Vector2f minExtents, final Vector2f maxExtents) {
		if(minExtents.getX() > maxExtents.getX() || minExtents.getY() > maxExtents.getY()) {
			System.err.println("Error, minExtents is greater than maxExtents");
			System.err.println("minExtents: " + minExtents + " ; maxExtents: " + maxExtents);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.setMinExtents(minExtents);
		this.setMaxExtents(maxExtents);
	}

	@Override
	public void transform(final Vector2f translation) {
		this.minExtents.addition(translation);
		this.maxExtents.addition(translation);
	}

	@Override
	public Vector2f getCenter() {
		final float x1 = this.minExtents.getX();
		final float x2 = this.maxExtents.getX();
		final float y1 = this.minExtents.getY();
		final float y2 = this.maxExtents.getY();
		return new Vector2f((x1 + x2) / 2, (y1 + y2) / 2);
	}

	/**
	 * Returns an IntersectData between the AABB and another AABB.
	 *
	 * @param aabb AABB to check with
	 * @return new IntersectData
	 */
	final public OldIntersectData intersectAABB(final OldAABB aabb) {
		final Vector2f distances1 = aabb.minExtents.sub(this.maxExtents);
		final Vector2f distances2 = this.minExtents.sub(aabb.maxExtents);
		final Vector2f distances = distances1.max(distances2);

		// TODO: Check if I do not need the diagonal
		final float maxDistance = distances.maxValue();

		return new OldIntersectData(maxDistance < 0, maxDistance);
	}

	/**
	 * Returns an IntersectData between the AABB and a circle.
	 *
	 * @param circle Circle to check with
	 * @return new IntersectData
	 */
	final public OldIntersectData intersectCircle(final OldCircle circle) {
		final Vector2f point = circle.getCenter();

		if(point.getX() < this.minExtents.getX()) {
			point.setX(this.minExtents.getX());
		} else if(point.getX() > this.maxExtents.getX()) {
			point.setX(this.maxExtents.getX());
		}
		if(point.getY() < this.minExtents.getY()) {
			point.setY(this.minExtents.getY());
		} else if(point.getY() > this.maxExtents.getY()) {
			point.setY(this.maxExtents.getY());
		}

		final float distance = point.distanceTo(circle.getCenter()) - circle.getRadius();
		return new OldIntersectData(distance < 0, distance);
	}

	/**
	 * Returns a copy of the smallest AABB's point.
	 *
	 * @return AABB.minExtents
	 */
	final public Vector2f getMinExtents() {
		return new Vector2f(this.minExtents);
	}

	/**
	 * Returns a copy of the biggest AABB's point.
	 *
	 * @return AABB.maxExtents
	 */
	final public Vector2f getMaxExtents() {
		return new Vector2f(this.maxExtents);
	}

	/**
	 * Sets the AABB's min extents.
	 *
	 * @param minExtents Min extents to set
	 */
	private void setMinExtents(final Vector2f minExtents) {
		if(minExtents.getX() > this.getMaxExtents().getX() || minExtents.getY() > this.getMaxExtents().getY()) {
			System.err.println("Error: AABB's minimum extents are greater than the max extents.");
			System.err.println("Min: " + minExtents + " --- Max: " + this.getMaxExtents());
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.minExtents = minExtents;
	}

	/**
	 * Sets the AABB's max extents.
	 *
	 * @param maxExtents Max extents to set
	 */
	private void setMaxExtents(final Vector2f maxExtents) {
		if(maxExtents.getX() < this.getMinExtents().getX() || maxExtents.getY() < this.getMinExtents().getY()) {
			System.err.println("Error: AABB's maximum extents are lower than the min extents.");
			System.err.println("Min: " + this.getMinExtents() + " --- Max: " + maxExtents);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.maxExtents = maxExtents;
	}

}