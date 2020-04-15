package engine.oldPhysic;

import engine.math.Vector2f;
import engine.oldPhysic.colliders.OldCollider;

public class OldPhysicsObject {

	/**
	 * Physics' object position.
	 */
	private Vector2f position;

	/**
	 * Physics' object old position.
	 */
	private Vector2f oldPosition;

	/**
	 * Physics' object velocity/speed.
	 */
	private Vector2f velocity;

	/**
	 * Physics' object collider.
	 */
	final private OldCollider collider;

	/**
	 * Creates a new PhysicsObject instance.
	 *
	 * @param collider Collider to set
	 * @param velocity Velocity/speed to set
	 */
	public OldPhysicsObject(final OldCollider collider, final Vector2f velocity) {
		this.setPosition(collider.getCenter());
		this.setOldPosition(this.getPosition());
		this.setVelocity(velocity);
		this.collider = collider;
	}

	/**
	 * Integrates/moves the PhysicsObject.
	 *
	 * @param delta How much time
	 */
	final public void integrate(final double delta) {
		this.setPosition(this.getPosition().add(this.getVelocity().mul((float) delta)));
	}

	/**
	 * Returns a copy of the PhysicsObject's position.
	 *
	 * @return new Vector2f
	 */
	final public Vector2f getPosition() {
		return new Vector2f(this.position);
	}

	/**
	 * Returns a copy of the PhysicsObject's old position.
	 *
	 * @return new Vector2f
	 */
	final public Vector2f getOldPosition() {
		return new Vector2f(this.oldPosition);
	}

	/**
	 * Returns a copy of the PhysicsObject's velocity.
	 *
	 * @return new Vector2f
	 */
	final public Vector2f getVelocity() {
		return new Vector2f(this.velocity);
	}

	/**
	 * Returns the PhysicsObject's collider and updates it.
	 *
	 * @return new Collider
	 */
	final public OldCollider getCollider() {
		final Vector2f translation = this.getPosition().sub(this.getOldPosition());
		this.setOldPosition(this.getPosition());
		this.collider.transform(translation);

		return this.collider;
	}

	/**
	 * Sets the PhysicsObject's position.
	 *
	 * @param position Position to set
	 */
	private void setPosition(final Vector2f position) {
		this.position = position;
	}

	/**
	 * Sets the PhysicsObject's old position.
	 *
	 * @param oldPosition Position to set
	 */
	private void setOldPosition(final Vector2f oldPosition) {
		this.oldPosition = oldPosition;
	}

	/**
	 * Sets the PhysicsObject's velocity/speed.
	 *
	 * @param velocity Velocity to set
	 */
	final void setVelocity(final Vector2f velocity) {
		this.velocity = velocity;
	}

}