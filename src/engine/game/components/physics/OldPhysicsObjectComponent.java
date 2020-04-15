package engine.game.components.physics;

import engine.game.components.GameComponent;
import engine.math.Vector2f;
import engine.oldPhysic.OldPhysicsObject;

public class OldPhysicsObjectComponent extends GameComponent {

	/**
	 * Pointer to the PhysicsObject.
	 */
	final private OldPhysicsObject object;

	/**
	 * Creates a new PhysicsObjectComponent instance.
	 *
	 * @param object PhysicsObject to set
	 */
	public OldPhysicsObjectComponent(final OldPhysicsObject object) {
		this.object = object;
	}

	@Override
	public void update(final double delta) {
		final Vector2f pos = this.getPhysicsObject().getPosition();

		this.getTransform().setPosition(pos);
	}

	/**
	 * Returns the pointer to the PhysicsObject.
	 *
	 * @return PhysicsObjectComponent.object
	 */
	private OldPhysicsObject getPhysicsObject() {
		return this.object;
	}

}