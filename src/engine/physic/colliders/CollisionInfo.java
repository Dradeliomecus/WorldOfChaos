package engine.physic.colliders;

import engine.util.Position;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CollisionInfo {

	/**
	 * A collider that represents everywhere the 2 shapes intersect.
	 */
	private @NotNull Collider collisionArea;

	/**
	 * A point where the collision began.
	 */
	private @Nullable Position collisionPoint;

	/**
	 * Creates a new CollisionInfo instance.
	 *
	 * @param collisionArea Shape that represents everywhere there is an intersection.
	 * @param collisionPoint Point where the collision began (sends a copy and not the pointer).
	 */
	CollisionInfo(final @NotNull Collider collisionArea, final @Nullable Position collisionPoint) {
		this.collisionArea = collisionArea;
		this.collisionPoint = collisionPoint == null ? null : new Position(collisionPoint);
	}

	/**
	 * Returns everywhere the 2 shapes intersect.
	 *
	 * @return CollisionInfo.collisionArea
	 */
	@Contract(pure = true)
	final public @NotNull Collider getCollisionArea() {
		return this.collisionArea;
	}

	/**
	 * Returns where the intersection began (returns a copy and not the pointer).
	 *
	 * @return CollisionInfo.collisionPoint
	 */
	@Contract(pure = true)
	final public @Nullable Position getCollisionPoint() {
		return collisionPoint == null ? null : new Position(this.collisionPoint);
	}

}