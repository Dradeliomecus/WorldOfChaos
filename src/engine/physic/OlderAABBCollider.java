package engine.physic;

import engine.math.Vector2f;
import org.jetbrains.annotations.NotNull;

public class OlderAABBCollider extends PhysicsObject {

	/**
	 * Creates a new AABBCollider instance.
	 *
	 * @param pos Position (in openGL pos)
	 * @param width Width (in openGL width)
	 * @param height Height (in openGL pos)
	 * @param movements Movements allowed
	 */
	public OlderAABBCollider(final @NotNull Vector2f pos, final float width, final float height, final MovementsAllowed movements) {
		super("Basic AABB Collider", width, height, CollisionBehaviour.OBJECT, movements);

		this.setPosition(pos);
	}

	/**
	 * Creates a new AABBCollider instance.
	 *
	 * @param pos Position (in openGL pos)
	 * @param width Width (in openGL width)
	 * @param height Height (in openGL pos)
	 */
	public OlderAABBCollider(final @NotNull Vector2f pos, final float width, final float height) {
		super("Basic AABB Collider", width, height, CollisionBehaviour.OBJECT, MovementsAllowed.IMMOBILE);

		this.setPosition(pos);
	}

	/**
	 * Creates a new AABBCollider instance.
	 *
	 * @param x X position (in openGL pos)
	 * @param y Y position (in openGL pos)
	 * @param width Width (in openGL pos)
	 * @param height Height (in openGL pos)
	 * @param movements Movements allowed
	 */
	public OlderAABBCollider(final float x, final float y, final float width, final float height, final MovementsAllowed movements) {
		this(new Vector2f(x, y), width, height, movements);
	}

	/**
	 * Creates a new AABBCollider instance.
	 *
	 * @param x X position (in openGL pos)
	 * @param y Y position (in openGL pos)
	 * @param width Width (in openGL pos)
	 * @param height Height (in openGL pos)
	 */
	public OlderAABBCollider(final float x, final float y, final float width, final float height) {
		this(new Vector2f(x, y), width, height, MovementsAllowed.IMMOBILE);
	}

	/**
	 * Creates a new AABBCollider instance.
	 *
	 * @param minExtents Bottom-left corner
	 * @param maxExtents Top-right corner
	 */
	public OlderAABBCollider(final @NotNull Vector2f minExtents, final @NotNull Vector2f maxExtents) {
		this(minExtents, maxExtents.getX() - minExtents.getX(), maxExtents.getY() - minExtents.getY());

		if(maxExtents.getX() < minExtents.getX() || maxExtents.getX() < minExtents.getY()) { // Check if information is accurate.
			System.err.println("Error: max extends is not greater than min extends");
			System.err.println("Min extents: " + minExtents.toString());
			System.err.println("Max extents: " + maxExtents.toString());
			new Exception().printStackTrace();
		}
	}

}