package engine.util;

import engine.math.Vector3f;

final public class Color{

	/**
	 * Color Black.
	 */
	final public static Color BLACK = new Color(0, 0, 0);

	/**
	 * Color Blue.
	 */
	final public static Color BLUE = new Color(0, 0, 255);

	/**
	 * Color Cyan.
	 */
	final public static Color CYAN = new Color(0, 255, 255);

	/**
	 * Color Green.
	 */
	final public static Color GREEN = new Color(0, 123, 0);

	/**
	 * Color Grey (mid-white, mid-black).
	 */
	final public static Color GREY = new Color(123, 123, 123);

	/**
	 * Color Green.
	 */
	final public static Color LIME = new Color(0, 255, 0);

	/**
	 * Color Orange.
	 */
	final public static Color ORANGE = new Color(255, 123, 0);

	/**
	 * Color Purple.
	 */
	final public static Color PURPLE = new Color(255, 0, 255);

	/**
	 * Color Red.
	 */
	final public static Color RED = new Color(255, 0, 0);

	/**
	 * Black with no alpha (nothing).
	 */
	final public static Color VACUUM = new Color(0, 0, 0, 0);

	/**
	 * Color White.
	 */
	final public static Color WHITE = new Color(255, 255, 255);

	/**
	 * Color Yellow.
	 */
	final public static Color YELLOW = new Color(255, 255, 0);

	/**
	 * Color's red amount (between 0 and 255).
	 */
	private int red;

	/**
	 * Color's green amount (between 0 and 255).
	 */
	private int green;

	/**
	 * Color's blue amount (between 0 and 255).
	 */
	private int blue;

	/**
	 * Color's alpha/opacity amount (between 0 and 1).
	 */
	private float alpha;

	/**
	 * Creates a new Color instance white.
	 */
	public Color() {
		this(Color.WHITE);
	}

	/**
	 * Creates a new Color instance with the same color.
	 *
	 * @param color Color to copy
	 */
	public Color(final Color color) {
		this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

	/**
	 * Creates a new Color instance with alpha = 1.
	 *
	 * @param red Red amount (between 0 and 255)
	 * @param green Green amount (between 0 and 255)
	 * @param blue Blue amount (between 0 and 255)
	 */
	public Color(final int red, final int green, final int blue) {
		this(red, green, blue, 1.0f);
	}

	/**
	 * Creates a new Color instance.
	 *
	 * @param red Red amount (between 0 and 255)
	 * @param green Green amount (between 0 and 255)
	 * @param blue Blue amount (between 0 and 255)
	 * @param alpha Alpha/Opacity amount (between 0 and 1)
	 */
	public Color(final int red, final int green, final int blue, final float alpha) {
		this.set(red, green, blue, alpha);
	}

	@Override
	public String toString() {
		return "rgba: " + this.getRed() + ',' + this.getGreen() + ',' + this.getBlue() + ',' + this.getAlpha();
	}

	/**
	 * Returns a Vector3f with x=red, y=green, z=blue
	 * All values are float between 0 and 1 (for OpenGL).
	 *
	 * @return Vector3f containing the color values
	 */
	final public Vector3f toVector3f() {
		return new Vector3f((float) this.getRed() / 255f, (float) this.getGreen() / 255f, (float) this.getBlue() / 255f);
	}

	/**
	 * Returns the Color's red amount (between 0 and 255).
	 *
	 * @return Color's red amount
	 */
	final public int getRed() {
		return this.red;
	}

	/**
	 * Returns the Color's green amount (between 0 and 255).
	 *
	 * @return Color's green amount
	 */
	final public int getGreen() {
		return this.green;
	}

	/**
	 * Returns the Color's blue amount (between 0 and 255).
	 *
	 * @return Color's blue amount
	 */
	final public int getBlue() {
		return this.blue;
	}

	/**
	 * Returns the Color's alpha/opacity amount (between 0 and 1).
	 *
	 * @return Color's alpha amount
	 */
	final public float getAlpha() {
		return this.alpha;
	}

	/**
	 * Sets the Color's red amount (between 0 and 255).
	 *
	 * @param red Red amount to set
	 */
	final public void setRed(final int red) {
		if(red < 0 || red > 255) {
			System.err.println("Error, trying to set a red amount to " + red + ". Only values between 0 and 255 are accepted");
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.red = red;
	}

	/**
	 * Sets the Color's green amount (between 0 and 255).
	 *
	 * @param green Green amount to set
	 */
	final public void setGreen(final int green) {
		if(green < 0 || green > 255) {
			System.err.println("Error, trying to set a green amount to " + green + ". Only values between 0 and 255 are accepted");
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.green = green;
	}

	/**
	 * Sets the Color's blue amount (between 0 and 255).
	 *
	 * @param blue Blue amount to set
	 */
	final public void setBlue(final int blue) {
		if(blue < 0 || blue > 255) {
			System.err.println("Error, trying to set a blue amount to " + blue + ". Only values between 0 and 255 are accepted");
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.blue = blue;
	}

	/**
	 * Sets the Color's alpha/opacity amount (between 0 and 1).
	 *
	 * @param alpha Alpha amount to set
	 */
	final public void setAlpha(final float alpha) {
		if(alpha < 0 || alpha > 1) {
			System.err.println("Error, trying to set a alpha amount to " + alpha + ". Only values between 0 and 1 are accepted");
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.alpha = alpha;
	}

	/**
	 * Sets the Color to the passed parameters.
	 *
	 * @param red Red amount to set (between 0 and 255)
	 * @param green Green amount to set (between 0 and 255)
	 * @param blue Blue amount to set (between 0 and 255)
	 * @param alpha Alpha/Opacity amount to set (between 0 and 1)
	 */
	final public void set(final int red, final int green, final int blue, final float alpha) {
		this.setRed(red);
		this.setGreen(green);
		this.setBlue(blue);
		this.setAlpha(alpha);
	}

	/**
	 * Sets the Color to the passed color.
	 *
	 * @param color Color to copy.
	 */
	final public void set(final Color color) {
		this.set(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

}