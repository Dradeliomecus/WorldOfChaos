package engine.rendering.texture;

public class Animation extends Image {

	/**
	 * Animation's textures.
	 */
	final private Texture[] textures;

	/**
	 * Time frame of the animation (in seconds).
	 */
	private double time;

	/**
	 * Animation's time (in seconds).
	 */
	private double maxTime;

	/**
	 * Creates a new Animation instance.
	 *
	 * @param textures Textures to set
	 * @param maxTime Animation length (in seconds)
	 */
	public Animation(final Texture[] textures, final double maxTime) {
		this.textures = textures;
		this.setTime(0);
		this.setMaxTime(maxTime);
	}

	@Override
	final Texture getTexture() {
		return this.getCurrentTexture();
	}

	@Override
	public void update(final double delta) {
		this.setTime((this.getTime() + delta) % this.getMaxTime());
	}

	/**
	 * Returns the Animation's textures.
	 *
	 * @return Animation.textures
	 */
	final protected Texture[] getTextures() {
		return this.textures;
	}

	/**
	 * Returns the current Animation's time frame.
	 *
	 * @return Animation.time
	 */
	private double getTime() {
		return this.time;
	}

	/**
	 * Returns the Animation's length.
	 *
	 * @return Animation.maxTime
	 */
	private double getMaxTime() {
		return this.maxTime;
	}

	/**
	 * Returns the current texture's index.
	 *
	 * @return Animation.textureIndex
	 */
	final protected int getTextureIndex() {
		return (int)(this.getTime() * this.getTextures().length / this.getMaxTime());
	}

	/**
	 * Returns the texture at a specific index.
	 *
	 * @param index Index to search for
	 * @return Texture
	 */
	final protected Texture getTexture(final int index) {
		return this.getTextures()[index];
	}

	/**
	 * Returns the current frame.
	 *
	 * @return texture
	 */
	final public Texture getCurrentTexture() {
		return this.getTexture(this.getTextureIndex());
	}

	/**
	 * Sets the Animation current time frame.
	 *
	 * @param time Time to set
	 */
	private void setTime(final double time) {
		this.time = time;
	}

	/**
	 * Sets the Animation length.
	 *
	 * @param maxTime Time to set
	 */
	final public void setMaxTime(final double maxTime) {
		this.maxTime = maxTime;
	}

}