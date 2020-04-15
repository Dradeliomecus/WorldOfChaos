package engine.game.components.physics;

import engine.game.components.GameComponent;
import engine.oldPhysic.OldPhysicsEngine;

public class OldPhysicsEngineComponent extends GameComponent {

	/**
	 * Pointer to the PhysicsEngine.
	 */
	final private OldPhysicsEngine engine;

	/**
	 * Creates a new PhysicsEngineComponent instance.
	 *
	 * @param engine Pointer to the PhysicsEngine
	 */
	public OldPhysicsEngineComponent(final OldPhysicsEngine engine) {
		this.engine = engine;
	}

	@Override
	public void update(final double delta) {
		this.getPhysicsEngine().simulate(delta);
		this.getPhysicsEngine().handleCollisions();
	}

	/**
	 * Returns the pointer to the PhysicsEngine.
	 *
	 * @return PhysicsEngineComponent.engine
	 */
	final public OldPhysicsEngine getPhysicsEngine() {
		return this.engine;
	}

}