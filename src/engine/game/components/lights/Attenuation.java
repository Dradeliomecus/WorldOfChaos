package engine.game.components.lights;

public class Attenuation {

	/**
	 * Attenuation's constant factor.
	 * This will reduce the emitted light everywhere
	 */
	private float constant;

	/**
	 * Attenuation's linear factor.
	 * This will reduce the emitted light with the distance
	 */
	private float linear;

	/**
	 * Attenuation's exponent factor.
	 * This will reduce the emitted light with the distance squared
	 */
	private float exponent;

	/**
	 * Creates a new Attenuation instance.
	 *
	 * @param constant Constant factor to set
	 * @param linear Linear factor to set
	 * @param exponent Exponent factor to set
	 */
	public Attenuation(final float constant, final float linear, final float exponent) {
		this.setConstant(constant);
		this.setLinear(linear);
		this.setExponent(exponent);
	}

	/**
	 * Returns the Attenuation's constant factor.
	 *
	 * @return Attenuation.constant
	 */
	final public float getConstant() {
		return this.constant;
	}

	/**
	 * Returns the Attenuation's linear factor.
	 *
	 * @return Attenuation.linear
	 */
	final public float getLinear() {
		return this.linear;
	}

	/**
	 * Returns the Attenuation's exponent factor.
	 *
	 * @return Attenuation.exponent
	 */
	final public float getExponent() {
		return this.exponent;
	}

	/**
	 * Sets the Attenuation's constant factor.
	 *
	 * @param constant Constant factor to set
	 */
	private void setConstant(final float constant) {
		this.constant = constant;
	}

	/**
	 * Sets the Attenuation's linear factor.
	 *
	 * @param linear Linear factor to set
	 */
	private void setLinear(final float linear) {
		this.linear = linear;
	}

	/**
	 * Sets the Attenuation's exponent factor.
	 *
	 * @param exponent Exponent factor to set
	 */
	private void setExponent(final float exponent) {
		this.exponent = exponent;
	}

}