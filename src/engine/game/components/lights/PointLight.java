package engine.game.components.lights;

import engine.math.Vector2f;
import engine.rendering.shader.Shader;
import engine.rendering.shader.forward.PointShader;
import engine.util.Color;
import org.jetbrains.annotations.NotNull;

public class PointLight extends BaseLight {

	/**
	 * PointLight's attenuation.
	 */
	private @NotNull Attenuation attenuation;

	/**
	 * PointLight's position.
	 */
	private @NotNull Vector2f position;

	/**
	 * PointLight's maximum range.
	 */
	private float range;

	/**
	 * Creates a new PointLight instance.
	 *
	 * @param color Color to set
	 * @param intensity Intensity to set
	 * @param shader Shader to set
	 * @param attenuation Attenuation to set
	 * @param position Position to set
	 */
	protected PointLight(final @NotNull Color color, final float intensity, final Shader shader, final @NotNull Attenuation attenuation, final @NotNull Vector2f position) {
		super(color, intensity, shader);

		final float a = attenuation.getExponent();
		final float b = attenuation.getLinear();
		final float c = attenuation.getConstant() - 256 * this.getIntensity() * this.getColor().toVector3f().maxValue();
		final float range = (float)((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));

		this.setAttenuation(attenuation);
		this.setPosition(position);
		this.setRange(range);

		this.setWidth(range);
		this.setHeight(range);
	}

	/**
	 * Creates a new PointLight instance.
	 *
	 * @param color Color to set
	 * @param intensity Intensity to set
	 * @param attenuation Attenuation to set
	 * @param position Position to set
	 */
	public PointLight(final Color color, final float intensity, final @NotNull Attenuation attenuation, final @NotNull Vector2f position) {
		this(color, intensity, PointShader.getInstance(), attenuation, position);
	}

	/**
	 * Creates a new PointLight instance.
	 *
	 * @param baseLight Base light to set
	 * @param attenuation Attenuation to set
	 * @param position Position to set
	 */
	public PointLight(final @NotNull BaseLight baseLight, final @NotNull Attenuation attenuation, final @NotNull Vector2f position) {
		this(baseLight.getColor(), baseLight.getIntensity(), attenuation, position);
	}

	/**
	 * Returns the PointLight's attenuation.
	 *
	 * @return PointLight.attenuation
	 */
	final public @NotNull Attenuation getAttenuation() {
		return this.attenuation;
	}

	/**
	 * Returns the PointLight's position.
	 *
	 * @return new Vector2f
	 */
	final public @NotNull Vector2f getPosition() {
		return this.getTransform().getPosition().add(this.position);
	}

	/**
	 * Returns the PointLight's range.
	 *
	 * @return PointLight.range
	 */
	final public float getRange() {
		return this.range;
	}

	/**
	 * Sets the PointLight's attenuation.
	 *
	 * @param attenuation Attenuation to set
	 */
	private void setAttenuation(final @NotNull Attenuation attenuation) {
		this.attenuation = attenuation;
	}

	/**
	 * Sets the PointLight's position.
	 *
	 * @param position Position to set
	 */
	private void setPosition(final @NotNull Vector2f position) {
		this.position = position;
	}

	/**
	 * Sets the PointLight's maximum range.
	 *
	 * @param range Range to set
	 */
	private void setRange(final float range) {
		this.range = range;
	}

}