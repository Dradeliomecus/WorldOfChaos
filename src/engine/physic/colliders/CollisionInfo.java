package engine.physic.colliders;

import engine.math.Vector2f;
import engine.util.Position;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CollisionInfo {

	/**
	 * Position of the original collider when collision occurs.
	 */
	final private @NotNull Position collisionPosition;

	/**
	 * A point where the collision began.
	 */
	final private @NotNull Position collisionPoint;

	/**
	 * Vector starting at collisionPoint that gives the tangent on collision.
	 */
	final private @Nullable Vector2f collisionTangent;

	/**
	 * Creates a new CollisionInfo instance.
	 *
	 * @param collisionPosition Position of original collider when collision occurs.
	 * @param collisionPoint Point where the collision began (sends a copy and not the pointer).
	 * @param collisionTangent Tangent with the collider during collision (vector starts at collisionPoint).
	 */
	CollisionInfo(final @NotNull Position collisionPosition, final @Nullable Position collisionPoint, final @Nullable Vector2f collisionTangent) {
		this.collisionPosition = collisionPosition;
		this.collisionPoint = collisionPoint == null ? null : new Position(collisionPoint);
		this.collisionTangent = collisionTangent == null ? null : new Vector2f(collisionTangent);
	}

	/**
	 * Returns the position of original collider when collision occurs (returns a copy and not the pointer).
	 *
	 * @return CollisionInfo.collisionArea
	 */
	@Contract(pure = true)
	final public @NotNull Position getCollisionPosition() {
		return new Position(this.collisionPosition);
	}

	/**
	 * Returns where the intersection began (returns a copy and not the pointer).
	 *
	 * @return CollisionInfo.collisionPoint
	 */
	@Contract(pure = true)
	final public @NotNull Position getCollisionPoint() {
		return new Position(this.collisionPoint);
	}

	/**
	 * Returns the collision tangent (returns a copy and not the pointer).
	 *
	 * @return CollisionInfo.collisionTangent
	 */
	@Contract(pure = true)
	final public @Nullable Vector2f getCollisionTangent() {
		return this.collisionTangent == null ? null : new Vector2f(this.collisionTangent);
	}

}