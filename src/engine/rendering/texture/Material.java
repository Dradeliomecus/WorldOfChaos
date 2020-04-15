package engine.rendering.texture;

import engine.util.Color;
import org.jetbrains.annotations.Contract;

final public class Material {

	/**
	 * Material's Image.
	 */
	private Image image;

	/**
	 * Material's Color.
	 */
	private Color color;

	/**
	 * Creates a new Material instance.
	 *
	 * @param image Image to set
	 */
	public Material(final Image image) {
		this(image, new Color());
	}

	/**
	 * Creates a new Material instance.
	 *
	 * @param image Image to set
	 * @param color Color to set
	 */
	public Material(final Image image, final Color color) {
		this.set(image, color);
	}

	/**
	 * Updates the Material.
	 *
	 * @param delta Delta
	 */
	final public void update(final double delta) {
		this.getImage().update(delta);
	}

	/**
	 * Returns the Material's image.
	 *
	 * @return Material.image
	 */
	@Contract(pure = true)
	final public Image getImage() {
		return this.image;
	}

	/**
	 * Returns the Material's texture.
	 *
	 * @return Material.image.texture
	 */
	final public Texture getTexture() {
		return this.getImage().getTexture();
	}

	/**
	 * Returns the Material's color.
	 *
	 * @return Material's color
	 */
	@Contract(pure = true)
	final public Color getColor() {
		return this.color;
	}

	/**
	 * Sets the Material's values.
	 *
	 * @param image Image to set
	 * @param color Color to set
	 */
	final public void set(final Image image, final Color color) {
		this.setImage(image);
		this.setColor(color);
	}

	/**
	 * Sets te Material's texture.
	 *
	 * @param image Image to set
	 */
	final public void setImage(final Image image) {
		this.image = image;
	}

	/**
	 * Sets the Material's color.
	 *
	 * @param color Color to set
	 */
	final public void setColor(final Color color) {
		this.color = color;
	}

}