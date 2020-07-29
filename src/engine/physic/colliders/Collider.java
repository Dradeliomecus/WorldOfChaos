package engine.physic.colliders;

import engine.util.Position;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Allows calculations of intersection, collisions with Position units.
 * This only checks the inside area. If 2 perimeters coincide perfectly, no intersection will be found.
 */
public abstract class Collider {

	/**
	 * Returns all points where there is a collision/intersection between (this) and the collider.
	 * Returns null if no collision was found (may be empty with collision under certain circumstances).
	 *
	 * @param collider Collider to check with
	 * @param prevPos Previous position of (this) collider.
	 * @return new CollisionInfo
	 */
	@Contract(pure = true)
	public abstract @Nullable CollisionInfo intersect(final @NotNull Collider collider, final @NotNull Position prevPos);

	/**
	 * Generic function that enables to calculate motion.
	 * Position can be any point, as long as translation of collider translate into translation of position.
	 *
	 * @return Position
	 */
	@Contract(pure = true)
	public abstract @NotNull Position getPosition();

	/**
	 * Returns whether the point is inside the collider.
	 *
	 * @param point Point to check
	 * @return new boolean
	 */
	@Contract(pure = true)
	public abstract boolean contains(final @NotNull Position point);

	/**
	 * Returns whether the point is on the collider (meaning the perimeter).
	 *
	 * @param point Point to check
	 * @return new boolean
	 */
	@Contract(pure = true)
	public abstract boolean on(final @NotNull Position point);

	/**
	 * Returns whether the point is inside on the the collider.
	 * By default returns contains() || on() but can be overridden for performance.
	 *
	 * @param point Point to check
	 * @return new boolean
	 */
	@Contract(pure = true)
	public boolean containsOrOn(final @NotNull Position point) {
		return this.contains(point) || this.on(point);
	}

	/**
	 * Returns the Collider's area.
	 *
	 * @return new int
	 */
	@Contract(pure = true)
	public abstract int area();

}