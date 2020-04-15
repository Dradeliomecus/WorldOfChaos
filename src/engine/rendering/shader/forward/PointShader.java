package engine.rendering.shader.forward;

import engine.game.Transform;
import engine.game.components.lights.Attenuation;
import engine.game.components.lights.PointLight;
import engine.rendering.texture.Material;
import engine.rendering.shader.Shader;
import org.jetbrains.annotations.NotNull;

public class PointShader extends Shader {

	/**
	 * PointShader's instance.
	 */
	final private static PointShader instance = new PointShader();

	/**
	 * Makes a new PointShader instance.
	 */
	private PointShader() {
		super("forward/point");
	}

	@Override
	final public void updateUniforms(final @NotNull Material material, final @NotNull Transform transform) {
		super.updateUniforms(material, transform);

		this.setUniform("pointLight", (PointLight) this.getRenderingEngine().getActiveLight());
	}

	/**
	 * Sets a PointLight into some uniforms.
	 *
	 * @param uniformName Uniform's name
	 * @param pointLight Point Light to set
	 */
	final public void setUniform(final @NotNull String uniformName, final @NotNull PointLight pointLight) {
		this.setUniform(uniformName + ".base.color", pointLight.getColor());
		this.setUniform(uniformName + ".base.intensity", pointLight.getIntensity());
		this.setUniform(uniformName + ".attenuation", pointLight.getAttenuation());
		this.setUniform(uniformName + ".position", pointLight.getPosition());
		this.setUniform(uniformName + ".range", pointLight.getRange());
	}

	/**
	 * Sets a Attenuation into some uniforms.
	 *
	 * @param uniformName Uniform's name
	 * @param attenuation Attenuation to set
	 */
	final public void setUniform(final @NotNull String uniformName, final @NotNull Attenuation attenuation) {
		this.setUniform(uniformName + ".constant", attenuation.getConstant());
		this.setUniform(uniformName + ".linear", attenuation.getLinear());
		this.setUniform(uniformName + ".exponent", attenuation.getExponent());
	}

	/**
	 * Returns the PointShader's instance.
	 *
	 * @return PointShader's instance
	 */
	public static @NotNull PointShader getInstance() {
		return PointShader.instance;
	}

}