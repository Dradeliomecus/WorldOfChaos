package engine.rendering.shader.forward;

import engine.game.Transform;
import engine.rendering.texture.Material;
import engine.rendering.shader.Shader;
import org.jetbrains.annotations.NotNull;

public class AmbientShader extends Shader {

	/**
	 * AmbientShader's instance.
	 */
	final private static @NotNull AmbientShader instance = new AmbientShader();

	/**
	 * Makes a new AmbientShader instance.
	 */
	private AmbientShader() {
		super("forward/ambient");
	}

	@Override
	final public void updateUniforms(final @NotNull Material material, final @NotNull Transform transform) {
		super.updateUniforms(material, transform);

		this.setUniform("ambientLight", this.getRenderingEngine().getAmbientLight());
	}

	/**
	 * Returns the AmbientShader's instance.
	 *
	 * @return AmbientShader's instance
	 */
	public static @NotNull AmbientShader getInstance() {
		return AmbientShader.instance;
	}

}