package engine.oldPhysic.colliders;

import engine.math.Vector2f;
import engine.oldPhysic.OldIntersectData;

abstract public class OldCollider {

	/**
	 * Creates a new Collider instance.
	 */
	protected OldCollider() {

	}

	/**
	 * Returns the IntersectData between the Collider and another Collider.
	 *
	 * @param other Collider to check with
	 * @return new IntersectData
	 */
	final public OldIntersectData intersect(final OldCollider other) {
		if(this instanceof OldAABB && other instanceof OldAABB) {
			return ((OldAABB) this).intersectAABB((OldAABB) other);
		}

		if(this instanceof OldAABB && other instanceof OldCircle) {
			return ((OldAABB) this).intersectCircle((OldCircle) other);
		}

		if(this instanceof OldCircle && other instanceof OldAABB) {
			return ((OldAABB) other).intersectCircle((OldCircle) this);
		}

		if(this instanceof OldCircle && other instanceof OldCircle) {
			return ((OldCircle) this).intersectCircle((OldCircle) other);
		}

		System.err.println("Error: Collision not implemented between this 2 colliders :");
		System.err.println(this + " ----- " + other);
		new Exception().printStackTrace();
		System.exit(1);

		return null;
	}

	/**
	 * Translates the collider by the translation in parameter.
	 *
	 * @param translation Translation to perform
	 */
	abstract public void transform(final Vector2f translation);

	/**
	 * Returns a copy of the Collider's center.
	 *
	 * @return new Vector2f
	 */
	abstract public Vector2f getCenter();

}