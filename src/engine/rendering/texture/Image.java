package engine.rendering.texture;

abstract class Image {

	/**
	 * Returns the Image's texture.
	 *
	 * @return Texture
	 */
	abstract Texture getTexture();

	/**
	 * Updates the Image.
	 *
	 * @param delta Delta
	 */
	public void update(final double delta) {

	}

}