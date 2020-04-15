package engine.game.components.lights;

import engine.math.Vector2f;
import engine.rendering.shader.forward.SpotShader;
import engine.util.Color;
import org.jetbrains.annotations.NotNull;

public class SpotLight extends PointLight {

	/**
	 * SpotLight's direction.
	 */
	private @NotNull Vector2f direction;

	/**
	 * SpotLight's cutoff (between 0 & 1, 0 is the biggest).
	 */
	private float cutoff;

	/**
	 * Creates a new SpotLight instance.
	 *
	 * @param color Color to set
	 * @param intensity Intensity to set
	 * @param attenuation Attenuation to set
	 * @param position Position to set
	 * @param direction Direction to set
	 * @param cutoff Cutoff to set
	 */
	public SpotLight(final @NotNull Color color, final float intensity, final @NotNull Attenuation attenuation, final @NotNull Vector2f position, final @NotNull Vector2f direction, final float cutoff) {
		this(new BaseLight(color, intensity, SpotShader.getInstance()), attenuation, position, direction, cutoff);
	}

	/**
	 * Creates a new SpotLight instance.
	 *
	 * @param baseLight Base light to set
	 * @param attenuation Intensity to set
	 * @param position Attenuation to set
	 * @param direction Direction to set
	 * @param cutoff Cutoff to set
	 */
	public SpotLight(final @NotNull BaseLight baseLight, final @NotNull Attenuation attenuation, final @NotNull Vector2f position, final @NotNull Vector2f direction, final float cutoff) {
		super(baseLight, attenuation, position);

		this.setDirection(direction);
		this.setCutoff(cutoff);
	}

	/**
	 * Returns the SpotLight's direction.
	 *
	 * @return SpotLight.direction
	 */
	final public @NotNull Vector2f getDirection() {
		return this.direction;
	}

	/**
	 * Returns the SpotLight's cutoff.
	 *
	 * @return SpotLight.cutoff
	 */
	final public float getCutoff() {
		return this.cutoff;
	}

	/**
	 * Sets the SpotLight's direction.
	 *
	 * @param direction Direction to set
	 */
	private void setDirection(final @NotNull Vector2f direction) {
		this.direction = direction.normalized();
	}

	/**
	 * Sets the SpotLight's cutoff.
	 *
	 * @param cutoff Cutoff to set
	 */
	private void setCutoff(final float cutoff) {
		this.cutoff = cutoff;
	}

}