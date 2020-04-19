package engine.util;

import engine.math.Vector2f;
import engine.math.Vector3f;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Position {

	/**
	 * How many is 1.0f of openGL unit.
	 */
	final public static int RATIO = 100000;

	/**
	 * Position on the x axis.
	 */
	private long x;

	/**
	 * Position on the y axis.
	 */
	private long y;

	/**
	 * Depth.
	 */
	private long z;

	/**
	 * Creates a new Position instance.
	 */
	public Position() {
		this(0, 0, 0);
	}

	/**
	 * Creates a new Position instance (integer).
	 *
	 * @param x Position on the x-axis
	 * @param y Position on the y-axis
	 */
	public Position(final long x, final long y) {
		this(x, y, 0);
	}

	/**
	 * Creates a new Position instance (integer).
	 *
	 * @param x Position on the x-axis
	 * @param y Position on the y-axis
	 * @param z Depth
	 */
	public Position(final long x, final long y, final long z) {
		this.setXYZ(x, y, z);
	}

	/**
	 * Creates a new Position instance (openGL units).
	 *
	 * @param x X position to set
	 * @param y Y position to set
	 */
	public Position(final float x, final float y) {
		this(x, y, 0);
	}

	/**
	 * Creates a new Position instance (openGL units).
	 *
	 * @param x X position to set
	 * @param y Y position to set
	 * @param z Depth to set
	 */
	public Position(final float x, final float y, final float z) {
		this.setXYZAsFloat(x, y, z);
	}

	/**
	 * Creates a new Position instance that's a copy of another position.
	 *
	 * @param pos Position to set
	 */
	public Position(final @NotNull Position pos) {
		this.set(pos);
	}

	/**
	 * Creates a new Position instance based on a Vector2f (openGL units).
	 *
	 * @param pos Position to set
	 */
	public Position(final @NotNull Vector2f pos) {
		this(pos, 0);
	}

	/**
	 * Creates a new Position instance based on a Vector2f + z (openGL units).
	 *
	 * @param pos Position to set
	 * @param z Depth to set
	 */
	public Position(final @NotNull Vector2f pos, final float z) {
		this.setXYZAsFloat(pos, z);
	}

	/**
	 * Creates a new Position instance based on a Vector3f.
	 *
	 * @param pos Position to set
	 */
	public Position(final @NotNull Vector3f pos) {
		this.setXYZAsFloat(pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public String toString() {
		return "Position: (" + this.getX() + " ; " + this.getY() + " ; " + this.getZ() +  ")";
	}

	@Contract(value = "null -> false", pure = true)
	@Override
	public boolean equals(final Object pos) {
		if(pos instanceof Position) {
			return this.getX() == ((Position) pos).getX() && this.getY() == ((Position) pos).getY() && this.getZ() == ((Position) pos).getZ();
		}

		return false;
	}

	/**
	 * Check if 2 positions have the same coordinates (not considering z axis).
	 *
	 * @param pos Position to check this with
	 * @return new boolean
	 */
	final public boolean equalsXY(final @NotNull Position pos) {
		return this.getX() == ((Position) pos).getX() && this.getY() == ((Position) pos).getY();
	}

	@Override
	final public int hashCode() {
		return (int)(2*this.getX() + 3*this.getY() + 5*this.getZ());
	}

	/**
	 * Converts an integer position into a float one (in openGL units).
	 *
	 * @param val Number to convert
	 * @return new float
	 */
	public static float convert(final long val) {
		return (float)((double)val/Position.RATIO);
	}

	/**
	 * Converts a float position (in openGL units) into an integer one.
	 *
	 * @param val Number to convert
	 * @return new long
	 */
	public static long convert(final float val) {
		return Math.round(val * Position.RATIO);
	}

	/**
	 * Returns the distance between (this) and the given position on the XY plane.
	 *
	 * @param pos Position to calculate with
	 * @return new int
	 */
	public int distanceToXY(final @NotNull Position pos) {
		final Position p = this.addXY(-pos.getX(), -pos.getY());
		return (int) Math.round(Math.sqrt(p.getX()*p.getX() + p.getY()*p.getY()));
	}

	/**
	 * Returns the x position (integer).
	 *
	 * @return Position.x
	 */
	@Contract(pure = true)
	final public long getX() {
		return this.x;
	}

	/**
	 * Returns the y position (integer).
	 *
	 * @return Position.y
	 */
	@Contract(pure = true)
	final public long getY() {
		return this.y;
	}

	/**
	 * Returns the depth (integer).
	 *
	 * @return Position.z
	 */
	@Contract(pure = true)
	final public long getZ() {
		return this.z;
	}

	/**
	 * Returns the x position (in openGL units).
	 *
	 * @return new float
	 */
	@Contract(pure = true)
	final public float getXAsFloat() {
		return Position.convert(this.getX());
	}

	/**
	 * Returns the y position (in openGL units).
	 *
	 * @return new float
	 */
	@Contract(pure = true)
	final public float getYAsFloat() {
		return Position.convert(this.getY());
	}

	/**
	 * Returns the depth (in openGL units).
	 *
	 * @return new float
	 */
	@Contract(pure = true)
	final public float getZAsFloat() {
		return Position.convert(this.getZ());
	}

	/**
	 * Returns a Vector2f of the position (in openGL units).
	 *
	 * @return new Vector2f
	 */
	final public @NotNull Vector2f asVector2f() {
		return new Vector2f(this.getXAsFloat(), this.getYAsFloat());
	}

	/**
	 * Returns a Vector3f of the position (in openGL units).
	 *
	 * @return new Vector3f
	 */
	final public @NotNull Vector3f asVector3f() {
		return new Vector3f(this.getXAsFloat(), this.getYAsFloat(), this.getZAsFloat());
	}

	/**
	 * Sets the x position (integer).
	 *
	 * @param x Position on the x-axis
	 */
	final public void setX(final long x) {
		this.x = x;
	}

	/**
	 * Sets the y position (integer).
	 *
	 * @param y Position on the y-axis
	 */
	final public void setY(final long y) {
		this.y = y;
	}

	/**
	 * Sets the depth (integer).
	 *
	 * @param z Depth
	 */
	final public void setZ(final long z) {
		this.z = z;
	}

	/**
	 * Sets the position (integer).
	 *
	 * @param x Position on the x-axis
	 * @param y Position on the y-axis
	 */
	final public void setXY(final long x, final long y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Sets the position (integer).
	 *
	 * @param x Position on the x-axis
	 * @param y Position on the y-axis
	 * @param z Depth
	 */
	final public void setXYZ(final long x, final long y, final long z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	/**
	 * Sets the position equals to another position.
	 *
	 * @param pos Position to set
	 */
	final public void set(final @NotNull Position pos) {
		this.setXYZ(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Sets the x position (in openGL units).
	 *
	 * @param x X position to set
	 */
	final public void setXAsFloat(final float x) {
		this.setX(Position.convert(x));
	}

	/**
	 * Sets the y position (in openGL units).
	 *
	 * @param y Y position to set
	 */
	final public void setYAsFloat(final float y) {
		this.setY(Position.convert(y));
	}

	/**
	 * Sets the depth (in openGL units).
	 *
	 * @param z Depth to set
	 */
	final public void setZAsFloat(final float z) {
		this.setZ(Position.convert(z));
	}

	/**
	 * Sets the position (in openGL units).
	 *
	 * @param x X position to set
	 * @param y Y position to set
	 */
	final public void setXYAsFloat(final float x, final float y) {
		this.setXAsFloat(x);
		this.setYAsFloat(y);
	}

	/**
	 * Sets the position (in openGL units).
	 *
	 * @param x X position to set
	 * @param y Y position to set
	 * @param z Depth to set
	 */
	final public void setXYZAsFloat(final float x, final float y, final float z) {
		this.setXAsFloat(x);
		this.setYAsFloat(y);
		this.setZAsFloat(z);
	}

	/**
	 * Sets the position (in openGL units).
	 *
	 * @param pos Position to set
	 */
	final public void setXYAsFloat(final @NotNull Vector2f pos) {
		this.setXYAsFloat(pos.getX(), pos.getY());
	}

	/**
	 * Sets the position (in openGL units).
	 *
	 * @param pos Position to set
	 * @param z Depth to set
	 */
	final public void setXYZAsFloat(final @NotNull Vector2f pos, final float z) {
		this.setXYZAsFloat(pos.getX(), pos.getY(), z);
	}

	/**
	 * Sets the position (in openGL units).
	 *
	 * @param pos Position to set
	 */
	final public void setXYZAsFloat(final @NotNull Vector3f pos) {
		this.setXYZAsFloat(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Returns a new Position that's this + x,y,z.
	 *
	 * @param x Amount to add on x-axis
	 * @param y Amount to add on y-axis
	 * @param z Amount to add on z-axis
	 * @return new Position
	 */
	final public @NotNull Position add(final long x, final long y, final long z) {
		return new Position(this.getX() + x, this.getY() + y, this.getZ() + z);
	}

	/**
	 * Returns a new Position that's this + pos.
	 *
	 * @param pos Position to add
	 * @return new Position
	 */
	final public @NotNull Position add(final @NotNull Position pos) {
		return this.add(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Returns a new Position that's this + x.
	 *
	 * @param x Amount to add
	 * @return new Position
	 */
	final public @NotNull Position addX(final long x) {
		return this.add(x, 0, 0);
	}

	/**
	 * Returns a new Position that's this + y.
	 *
	 * @param y Amount to add
	 * @return new Position
	 */
	final public @NotNull Position addY(final long y) {
		return this.add(0, y, 0);
	}

	/**
	 * Returns a new Position that's this + z.
	 *
	 * @param z Amount to add
	 * @return new Position
	 */
	final public @NotNull Position addZ(final long z) {
		return this.add(0, 0, z);
	}

	/**
	 * Returns a new Position that's this + x,y.
	 *
	 * @param x Amount to add on x-axis
	 * @param y Amount to add on y-axis
	 * @return new Position
	 */
	final public @NotNull Position addXY(final long x, final long y) {
		return this.add(x, y, 0);
	}

	/**
	 * Returns a new Position that's this + x,y,z.
	 *
	 * @param x Amount to add on the x-axis
	 * @param y Amount to add on the y-axis
	 * @param z Amount to add on the z-axis
	 * @return new Position
	 */
	final public @NotNull Position addAsFloat(final float x, final float y, final float z) {
		return this.add(Position.convert(x), Position.convert(y), Position.convert(z));
	}

	/**
	 * Returns a new Position that's this + x.
	 *
	 * @param x Amount to add
	 * @return new Position
	 */
	final public @NotNull Position addXAsFloat(final float x) {
		return this.addAsFloat(x, 0, 0);
	}

	/**
	 * Returns a new Position that's this + y.
	 *
	 * @param y Amount to add
	 * @return new Position
	 */
	final public @NotNull Position addYAsFloat(final float y) {
		return this.addAsFloat(0, y, 0);
	}

	/**
	 * Returns a new Position that's this + z.
	 *
	 * @param z Amount to add
	 * @return new Position
	 */
	final public @NotNull Position addZAsFloat(final float z) {
		return this.addAsFloat(0, 0, z);
	}

	/**
	 * Returns a new Position that's this + x,y.
	 *
	 * @param x Amount to add on x-axis
	 * @param y Amount to add on y-axis
	 * @return new Position
	 */
	final public @NotNull Position addXYAsFloat(final float x, final float y) {
		return this.addAsFloat(x, y, 0);
	}

	/**
	 * Returns a new Position that's this + x,y.
	 *
	 * @param pos Amount to add
	 * @return new Position
	 */
	final public @NotNull Position addXYAsFloat(final @NotNull Vector2f pos) {
		return this.addAsFloat(pos.getX(), pos.getY(), 0);
	}

	/**
	 * Adds some position on the x-axis (integer).
	 *
	 * @param x Amount to add
	 * @return this
	 */
	final public Position additionX(final long x) {
		this.x += x;
		return this;
	}

	/**
	 * Adds some position on the y-axis (integer).
	 *
	 * @param y Amount to add
	 * @return this
	 */
	final public Position additionY(final long y) {
		this.y += y;
		return this;
	}

	/**
	 * Adds some depth (integer).
	 *
	 * @param z Amount to add
	 * @return this
	 */
	final public Position additionZ(final long z) {
		this.z += z;
		return this;
	}

	/**
	 * Adds some position (integer).
	 *
	 * @param x Amount to add on the x-axis
	 * @param y Amount to add on the y-axis
	 * @return this
	 */
	final public Position additionXY(final long x, final long y) {
		this.additionX(x);
		this.additionY(y);
		return this;
	}

	/**
	 * Adds some position (integer).
	 *
	 * @param x Amount to add on the x-axis
	 * @param y Amount to add on the y-axis
	 * @param z Amount of depth to add
	 * @return this
	 */
	final public Position addition(final long x, final long y, final long z) {
		this.additionX(x);
		this.additionY(y);
		this.additionZ(z);
		return this;
	}

	/**
	 * Adds some position.
	 *
	 * @param pos Position to add
	 * @return this
	 */
	final public @NotNull Position addition(final @NotNull Position pos) {
		return this.addition(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Adds some position on the x-axis (in openGL units).
	 *
	 * @param x Amount to add
	 * @return this
	 */
	final public Position additionXAsFloat(final float x) {
		return this.additionX(Position.convert(x));
	}

	/**
	 * Adds some position on the y-axis (in openGL units).
	 *
	 * @param y Amount to add
	 * @return this
	 */
	final public Position additionYAsFloat(final float y) {
		return this.additionY(Position.convert(y));
	}

	/**
	 * Adds some depth (in openGL units).
	 *
	 * @param z Amount to add
	 * @return this
	 */
	final public Position additionZAsFloat(final float z) {
		return this.additionZ(Position.convert(z));
	}

	/**
	 * Adds some position (in openGL units).
	 *
	 * @param x Amount to add on the x-axis
	 * @param y Amount to add on the y-axis
	 * @return this
	 */
	final public Position additionXYAsFloat(final float x, final float y) {
		this.additionXAsFloat(x);
		this.additionYAsFloat(y);
		return this;
	}

	/**
	 * Adds some position (in openGL units).
	 *
	 * @param x Amount to add on the x-axis
	 * @param y Amount to add on the y-axis
	 * @param z Amount of depth to add
	 * @return this
	 */
	final public Position additionAsFloat(final float x, final float y, final float z) {
		this.additionXAsFloat(x);
		this.additionYAsFloat(y);
		this.additionZAsFloat(z);
		return this;
	}

	/**
	 * Adds some position (in openGL units).
	 *
	 * @param pos Amount to add
	 * @return this
	 */
	final public Position additionXYAsFloat(final @NotNull Vector2f pos) {
		return this.additionXYAsFloat(pos.getX(), pos.getY());
	}

	/**
	 * Adds some position (in openGL units).
	 *
	 * @param pos Amount to add on the xy-axis
	 * @param z Amount of depth to add
	 * @return this
	 */
	final public Position additionAsFloat(final @NotNull Vector2f pos, final float z) {
		return this.additionAsFloat(pos.getX(), pos.getY(), z);
	}

	/**
	 * Adds some position (in openGL units).
	 *
	 * @param pos Amount to add
	 * @return this
	 */
	final public Position additionAsFloat(final @NotNull Vector3f pos) {
		return this.additionAsFloat(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Returns a new Position that's this - x,y,z.
	 *
	 * @param x Amount to subtract on x-axis
	 * @param y Amount to subtract on y-axis
	 * @param z Amount to subtract on z-axis
	 * @return new Position
	 */
	final public @NotNull Position sub(final long x, final long y, final long z) {
		return new Position(this.getX() - x, this.getY() - y, this.getZ() - z);
	}

	/**
	 * Returns a new Position that's this - pos.
	 *
	 * @param pos Position to subtract
	 * @return new Position
	 */
	final public @NotNull Position sub(final @NotNull Position pos) {
		return this.sub(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Returns a new Position that's this - x.
	 *
	 * @param x Amount to subtract
	 * @return new Position
	 */
	final public @NotNull Position subX(final long x) {
		return this.add(-x, 0, 0);
	}

	/**
	 * Returns a new Position that's this - y.
	 *
	 * @param y Amount to subtract
	 * @return new Position
	 */
	final public @NotNull Position subY(final long y) {
		return this.add(0, -y, 0);
	}

	/**
	 * Returns a new Position that's this - z.
	 *
	 * @param z Amount to subtract
	 * @return new Position
	 */
	final public @NotNull Position subZ(final long z) {
		return this.add(0, 0, -z);
	}

	/**
	 * Returns a new Position that's this - x,y.
	 *
	 * @param x Amount to subtract on x-axis
	 * @param y Amount to subtract on y-axis
	 * @return new Position
	 */
	final public @NotNull Position subXY(final long x, final long y) {
		return this.add(-x, -y, 0);
	}

	/**
	 * Returns a new Position that's this - x,y,z.
	 *
	 * @param x Amount to subtract on the x-axis
	 * @param y Amount to subtract on the y-axis
	 * @param z Amount to subtract on the z-axis
	 * @return new Position
	 */
	final public @NotNull Position subAsFloat(final float x, final float y, final float z) {
		return this.add(-Position.convert(x), -Position.convert(y), -Position.convert(z));
	}

	/**
	 * Returns a new Position that's this - x.
	 *
	 * @param x Amount to subtract
	 * @return new Position
	 */
	final public @NotNull Position subXAsFloat(final float x) {
		return this.addAsFloat(-x, 0, 0);
	}

	/**
	 * Returns a new Position that's this - y.
	 *
	 * @param y Amount to subtract
	 * @return new Position
	 */
	final public @NotNull Position subYAsFloat(final float y) {
		return this.addAsFloat(0, -y, 0);
	}

	/**
	 * Returns a new Position that's this - z.
	 *
	 * @param z Amount to subtract
	 * @return new Position
	 */
	final public @NotNull Position subZAsFloat(final float z) {
		return this.addAsFloat(0, 0, -z);
	}

	/**
	 * Returns a new Position that's this - x,y.
	 *
	 * @param x Amount to subtract on x-axis
	 * @param y Amount to subtract on y-axis
	 * @return new Position
	 */
	final public @NotNull Position subXYAsFloat(final float x, final float y) {
		return this.addAsFloat(-x, -y, 0);
	}

	/**
	 * Returns a new Position that's this - x,y.
	 *
	 * @param pos Amount to subtract
	 * @return new Position
	 */
	final public @NotNull Position subXYAsFloat(final @NotNull Vector2f pos) {
		return this.addAsFloat(-pos.getX(), -pos.getY(), 0);
	}

	/**
	 * Subtracts some position on the x-axis (integer).
	 *
	 * @param x Amount to subtract
	 * @return this
	 */
	final public Position subtractX(final long x) {
		this.x -= x;
		return this;
	}

	/**
	 * Subtracts some position on the y-axis (integer).
	 *
	 * @param y Amount to subtract
	 * @return this
	 */
	final public Position subtractY(final long y) {
		this.y -= y;
		return this;
	}

	/**
	 * Subtracts some depth (integer).
	 *
	 * @param z Amount to subtract
	 * @return this
	 */
	final public Position subtractZ(final long z) {
		this.z -= z;
		return this;
	}

	/**
	 * Subtracts some position (integer).
	 *
	 * @param x Amount to subtract on the x-axis
	 * @param y Amount to subtract on the y-axis
	 * @return this
	 */
	final public Position subtractXY(final long x, final long y) {
		this.subtractX(x);
		this.subtractY(y);
		return this;
	}

	/**
	 * Subtracts some position (integer).
	 *
	 * @param x Amount to subtract on the x-axis
	 * @param y Amount to subtract on the y-axis
	 * @param z Amount of depth to subtract
	 * @return this
	 */
	final public Position subtract(final long x, final long y, final long z) {
		this.subtractX(x);
		this.subtractY(y);
		this.subtractZ(z);
		return this;
	}

	/**
	 * Subtracts some position.
	 *
	 * @param pos Position to subtract
	 * @return this
	 */
	final public @NotNull Position subtract(final @NotNull Position pos) {
		return this.subtract(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Subtracts some position on the x-axis (in openGL units).
	 *
	 * @param x Amount to subtract
	 * @return this
	 */
	final public Position subtractXAsFloat(final float x) {
		return this.subtractX(Position.convert(x));
	}

	/**
	 * Subtracts some position on the y-axis (in openGL units).
	 *
	 * @param y Amount to subtract
	 * @return this
	 */
	final public Position subtractYAsFloat(final float y) {
		return this.subtractY(Position.convert(y));
	}

	/**
	 * Subtracts some depth (in openGL units).
	 *
	 * @param z Amount to subtract
	 * @return this
	 */
	final public Position subtractZAsFloat(final float z) {
		return this.subtractZ(Position.convert(z));
	}

	/**
	 * Subtracts some position (in openGL units).
	 *
	 * @param x Amount to subtract on the x-axis
	 * @param y Amount to subtract on the y-axis
	 * @return this
	 */
	final public Position subtractXYAsFloat(final float x, final float y) {
		this.subtractXAsFloat(x);
		this.subtractYAsFloat(y);
		return this;
	}

	/**
	 * Subtracts some position (in openGL units).
	 *
	 * @param x Amount to subtract on the x-axis
	 * @param y Amount to subtract on the y-axis
	 * @param z Amount of depth to subtract
	 * @return this
	 */
	final public Position subtractAsFloat(final float x, final float y, final float z) {
		this.subtractXAsFloat(x);
		this.subtractYAsFloat(y);
		this.subtractZAsFloat(z);
		return this;
	}

	/**
	 * Subtracts some position (in openGL units).
	 *
	 * @param pos Amount to subtract
	 * @return this
	 */
	final public Position subtractXYAsFloat(final @NotNull Vector2f pos) {
		return this.subtractXYAsFloat(pos.getX(), pos.getY());
	}

	/**
	 * Subtracts some position (in openGL units).
	 *
	 * @param pos Amount to subtract on the xy-axis
	 * @param z Amount of depth to subtract
	 * @return this
	 */
	final public Position subtractAsFloat(final @NotNull Vector2f pos, final float z) {
		return this.subtractAsFloat(pos.getX(), pos.getY(), z);
	}

	/**
	 * Subtracts some position (in openGL units).
	 *
	 * @param pos Amount to subtract
	 * @return this
	 */
	final public Position subtractAsFloat(final @NotNull Vector3f pos) {
		return this.subtractAsFloat(pos.getX(), pos.getY(), pos.getZ());
	}

}