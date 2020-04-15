package engine.oldPhysic;

final public class OldIntersectData {

	/**
	 * Is there an intersection.
	 */
	private boolean doesIntersect;

	/**
	 * Distance (how deep does it collide / how far is it to collide).
	 */
	private float distance;

	/**
	 * Creates a new IntersectData instance.
	 *
	 * @param doesIntersect Does it intersect
	 * @param distance Distance from collision / depth of collision
	 */
	public OldIntersectData(final boolean doesIntersect, final float distance) {
		this.setIntersect(doesIntersect);
		this.setDistance(distance);
	}

	@Override
	final public String toString() {
		final String str = "Intersect : " + this.doesIntersect + " ; Distance : " + this.getDistance();

		return super.toString() + " " + str;
	}

	/**
	 * Returns if there is a collision.
	 *
	 * @return Collision occurred
	 */
	final public boolean doesIntersect() {
		return this.doesIntersect;
	}

	/**
	 * Returns the distance from collision / depth of collision.
	 *
	 * @return Distance
	 */
	final public float getDistance() {
		return this.distance;
	}

	/**
	 * Sets if there is a collision.
	 *
	 * @param doesIntersect Intersection
	 */
	private void setIntersect(final boolean doesIntersect) {
		this.doesIntersect = doesIntersect;
	}

	/**
	 * Sets the distance from collision / depth of collision.
	 *
	 * @param distance Distance to set
	 */
	private void setDistance(final float distance) {
		this.distance = distance;
	}

}