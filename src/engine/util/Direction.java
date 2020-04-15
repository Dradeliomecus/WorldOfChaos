package engine.util;

import engine.math.Vector2f;
import org.jetbrains.annotations.NotNull;

final public class Direction { // TODO: Should be an enum.

	/**
	 * No Direction.
	 */
	final public static byte NONE = 0;

	/**
	 * North direction (up).
	 */
	final public static byte NORTH = 1;

	/**
	 * North-west direction (up-left).
	 */
	final public static byte NORTH_WEST = 2;

	/**
	 * North-east direction (up-right).
	 */
	final public static byte NORTH_EAST = 3;

	/**
	 * West direction (left).
	 */
	final public static byte WEST = 4;

	/**
	 * East direction (right).
	 */
	final public static byte EAST = 5;

	/**
	 * South-west direction (down-left).
	 */
	final public static byte SOUTH_WEST = 6;

	/**
	 * South-east direction (down-right).
	 */
	final public static byte SOUTH_EAST = 7;

	/**
	 * South direction (down).
	 */
	final public static byte SOUTH = 8;

	/**
	 * Direction's direction.
	 */
	private byte direction;

	/**
	 * Creates a new Direction instance (with value NONE).
	 */
	public Direction() {
		this(Direction.NONE);
	}

	@Override
	final public @NotNull String toString() {
		return super.toString() + " - " + this.getDirectionName();
	}

	/**
	 * Returns the Vector2f version of the direction.
	 *
	 * @return Vector2f version of this
	 */
	final public Vector2f toVector2f() {
		return Direction.getVectorFromDirection(this);
	}

	/**
	 * Creates a new Direction instance.
	 *
	 * @param direction Direction to set
	 */
	public Direction(final @NotNull Direction direction) {
		this(direction.getDirection());
	}

	/**
	 * Creates a new Direction instance.
	 *
	 * @param direction Direction to set
	 */
	public Direction(final byte direction) {
		this.setDirection(direction);
	}

	/**
	 * Returns the direction name (NONE, NORTH etc.) of the Direction
	 *
	 * @return Direction's name
	 */
	final public @NotNull String getDirectionName() {
		final byte dir = this.getDirection();

		if(dir == Direction.NONE) {
			return "NONE";
		} else if(dir == Direction.NORTH) {
			return "NORTH";
		} else if(dir == Direction.NORTH_WEST) {
			return "NORTH_WEST";
		} else if(dir == Direction.NORTH_EAST) {
			return "NORTH_EAST";
		} else if(dir == Direction.WEST) {
			return "WEST";
		} else if(dir == Direction.EAST) {
			return "EAST";
		} else if(dir == Direction.SOUTH) {
			return "SOUTH";
		} else if(dir == Direction.SOUTH_WEST) {
			return "SOUTH_WEST";
		} else if(dir == Direction.SOUTH_EAST) {
			return "SOUTH_EAST";
		} else {
			System.err.println("Error: Direction's direction is an incorrect value: " + dir);
			new Exception().printStackTrace();
			return "";
		}
	}

	/**
	 * Returns the Direction's direction.
	 *
	 * @return Direction's direction
	 */
	final public byte getDirection() {
		return this.direction;
	}

	/**
	 * Sets the Direction's direction.
	 *
	 * @param direction Direction to set
	 */
	final public void setDirection(final byte direction) {
		this.direction = direction;

		if(this.getDirectionName().equals("")) {
			System.err.println("Error: direction was set to '" + direction + "' but is not valid.");
			new Exception().printStackTrace();
		}
	}

	/**
	 * Returns a direction that correspond to the Vector2f.
	 *
	 * @param dir Direction to calculate with
	 * @return Direction that correspond to the Vector2f
	 */
	public static Direction getDirectionFromVector(final Vector2f dir) {
		final float x = dir.getX();
		final float y = dir.getY();

		if(x > 0) {
			if(y > 0)       return new Direction(Direction.NORTH_EAST);
			else if(y < 0)  return new Direction(Direction.SOUTH_EAST);
			else            return new Direction(Direction.EAST);
		} else if(x < 0) {
			if(y > 0)       return new Direction(Direction.NORTH_WEST);
			else if(y < 0)  return new Direction(Direction.SOUTH_WEST);
			else            return new Direction(Direction.WEST);
		} else {
			if(y > 0)       return new Direction(Direction.NORTH);
			else if(y < 0)  return new Direction(Direction.SOUTH);
			else            return new Direction(Direction.NONE);
		}
	}

	/**
	 * Returns a Vector2f that correspond to the Direction.
	 *
	 * @param direction Vector2f to calculate with
	 * @return Vector2f that correspond to the Direction
	 */
	public static Vector2f getVectorFromDirection(final Direction direction) {
		final byte dir = direction.getDirection();

		if(dir == Direction.NONE) {
			return new Vector2f(0, 0);
		} else if(dir == Direction.NORTH) {
			return new Vector2f(0, 1);
		} else if(dir == Direction.NORTH_WEST) {
			return new Vector2f(-1, 1);
		} else if(dir == Direction.NORTH_EAST) {
			return new Vector2f(1, 1);
		} else if(dir == Direction.WEST) {
			return new Vector2f(-1, 0);
		} else if(dir == Direction.EAST) {
			return new Vector2f(1, 0);
		} else if(dir == Direction.SOUTH) {
			return new Vector2f(0, -1);
		} else if(dir == Direction.SOUTH_WEST) {
			return new Vector2f(-1, -1);
		} else if(dir == Direction.SOUTH_EAST) {
			return new Vector2f(1, -1);
		} else {
			System.err.println("Error: Direction's direction is an incorrect value: " + dir);
			new Exception().printStackTrace();
			System.exit(1);
			return null;
		}
	}

}