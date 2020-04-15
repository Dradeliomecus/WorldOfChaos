package engine.rendering.shader.forward;

import engine.game.Transform;
import engine.game.components.lights.Attenuation;
import engine.game.components.lights.SpotLight;
import engine.math.Matrix4f;
import engine.rendering.texture.Material;
import engine.rendering.shader.Shader;
import org.jetbrains.annotations.NotNull;

public class SpotShader extends Shader {

	/**
	 * SpotShader's instance.
	 */
	final private static SpotShader instance = new SpotShader();

	/**
	 * Makes a new SpotShader instance.
	 */
	private SpotShader() {
		super("forward/spot");

		this.setAttribLocation(0, "position");
		this.setAttribLocation(1, "textCoord");

		this.compile();

		this.addUniform("transform");
		this.addUniform("transformProjected");

		this.addUniform("spotLight.pointLight.base.color");
		this.addUniform("spotLight.pointLight.base.intensity");
		this.addUniform("spotLight.pointLight.attenuation.constant");
		this.addUniform("spotLight.pointLight.attenuation.linear");
		this.addUniform("spotLight.pointLight.attenuation.exponent");
		this.addUniform("spotLight.pointLight.position");
		this.addUniform("spotLight.pointLight.range");
		this.addUniform("spotLight.direction");
		this.addUniform("spotLight.cutoff");
	}

	@Override
	final public void updateUniforms(final @NotNull Material material, final @NotNull Transform transform) {
		material.getTexture().bind();

		final Matrix4f projection = this.getRenderingEngine().getMainCamera().getProjectionMatrix();

		this.setUniform("transform", transform.getTransformedTransformation());
		this.setUniform("transformProjected", projection.mul(transform.getTransformedTransformation()));
		this.setUniform("spotLight", (SpotLight) this.getRenderingEngine().getActiveLight());
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
	 * Sets a SpotLight into some uniforms.
	 *
	 * @param uniformName Uniform's name
	 * @param spotLight Spot light to set
	 */
	final public void setUniform(final @NotNull String uniformName, final @NotNull SpotLight spotLight) {
		this.setUniform(uniformName + ".pointLight.base.color", spotLight.getColor());
		this.setUniform(uniformName + ".pointLight.base.intensity", spotLight.getIntensity());
		this.setUniform(uniformName + ".pointLight.attenuation", spotLight.getAttenuation());
		this.setUniform(uniformName + ".pointLight.position", spotLight.getPosition());
		this.setUniform(uniformName + ".pointLight.range", spotLight.getRange());
		this.setUniform(uniformName + ".direction", spotLight.getDirection());
		this.setUniform(uniformName + ".cutoff", spotLight.getCutoff());
	}

	/**
	 * Returns the SpotShader's instance.
	 *
	 * @return SpotShader's instance
	 */
	public static @NotNull SpotShader getInstance() {
		return SpotShader.instance;
	}

}