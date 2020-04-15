package engine.rendering.shader;

import engine.game.Transform;
import engine.math.Matrix4f;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class RenderToTextureShader extends Shader {

	/**
	 * RenderToTextureShader's instance.
	 */
	final private static RenderToTextureShader instance = new RenderToTextureShader();

	/**
	 * Texture's position scale.
	 */
	private static @NotNull Vector2f texturePositionScale = new Vector2f(1, 1);

	/**
	 * Texture's scale.
	 */
	private static @NotNull Vector2f textureScale = new Vector2f(1, 1);

	/**
	 * Makes a new RenderToTextureShader instance.
	 */
	private RenderToTextureShader() {
		super("render-to-texture");
	}

	@Override
	final public void updateUniforms(final @NotNull Material material, final @NotNull Transform transform) {
		material.getTexture().bind();

		final Vector2f scale = RenderToTextureShader.getTextureScale();

		final Matrix4f translationMatrix = new Matrix4f().initPosition(-1 + (transform.getTransformedPosition().getX()) * RenderToTextureShader.getTexturePositionScale().getX(), -1 + (transform.getTransformedPosition().getY()) * RenderToTextureShader.getTexturePositionScale().getY(), 0);
		final Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), 1);
		final Matrix4f m = translationMatrix.mul(scaleMatrix);
		this.setUniform("transform", m);
	}

	/**
	 * Returns the RenderToTextureShader's instance.
	 *
	 * @return RenderToTextureShader's instance
	 */
	@Contract(pure = true)
	public static @NotNull RenderToTextureShader getInstance() {
		return RenderToTextureShader.instance;
	}

	/**
	 * Returns the Texture's scale.
	 *
	 * @return RenderToTextureShader::textureScale
	 */
	@Contract(pure = true)
	private static @NotNull Vector2f getTextureScale() {
		return RenderToTextureShader.textureScale;
	}

	/**
	 * Returns the Texture's position scale.
	 *
	 * @return RenderToTextureShader::texturePositionScale
	 */
	@Contract(pure = true)
	private static @NotNull Vector2f getTexturePositionScale() {
		return RenderToTextureShader.texturePositionScale;
	}

	/**
	 * Sets the Texture's scale.
	 *
	 * @param scale Scale to copy and set
	 */
	public static void setTextureScale(final @NotNull Vector2f scale) {
		RenderToTextureShader.textureScale = new Vector2f(scale);
	}

	/**
	 * Sets the Texture's position scale.
	 *
	 * @param scale Scale to copy and set
	 */
	public static void setTexturePositionScale(final @NotNull Vector2f scale) {
		RenderToTextureShader.texturePositionScale = new Vector2f(scale);
	}

}