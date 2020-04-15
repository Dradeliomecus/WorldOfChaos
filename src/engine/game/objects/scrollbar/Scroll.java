package engine.game.objects.scrollbar;

import engine.game.objects.GameObject;
import engine.math.Vector2f;

public class Scroll extends GameObject {

	/**
	 * Height where the scroll will be moving.
	 */
	final private float scrollbarHeight;

	/**
	 * Height ratio (Object.height / Object.totalHeight)
	 */
	final private float heightRatio;

	/**
	 * Delta height (Object.totalHeight - Object.height).
	 */
	final private float deltaHeight;

	/**
	 * Amount scrolled since the beginning.
	 */
	private float scroll;

	/**
	 * Creates a new Scroll instance.
	 *
	 * @param name Scroll's name
	 * @param width Scroll's width
	 * @param scrollbarHeight Height where the scroll moves
	 * @param heightRatio Height ratio (Object.height / Object.totalHeight)
	 * @param deltaHeight Delta height (Object.totalHeight - Object.height)
	 * @param topScrollFragment Scroll's top fragment
	 * @param middleScrollFragment Scroll's middle fragment
	 * @param bottomScrollFragment Scroll's bottom fragment
	 */
	public Scroll(final String name, final float width, final float scrollbarHeight, final float heightRatio, final float deltaHeight, final ScrollFragment topScrollFragment, final ScrollFragment middleScrollFragment, final ScrollFragment bottomScrollFragment) {
		super("Scroll " + name, width, scrollbarHeight * heightRatio);

		this.scrollbarHeight = scrollbarHeight;
		this.heightRatio = heightRatio;
		this.deltaHeight = deltaHeight;

		this.setDepth(-0.01f);

		this.addChild(topScrollFragment);
		this.addChild(middleScrollFragment);
		this.addChild(bottomScrollFragment);
	}

	@Override
	public void update(final double delta) {
		super.update(delta);

		// The calculi is complicated, but I did the math, trust yourself !
		this.setPosition(new Vector2f(this.getObjectPosition().getX(), this.scrollbarHeight * (1 - this.getHeightRatio()) * (1 - this.getScroll() / this.deltaHeight) + 2.0f/256.0f));
	}

	/**
	 * Returns the amount scrolled since the beginning.
	 *
	 * @return Scrollbar.scroll
	 */
	final protected float getScroll() {
		return this.scroll;
	}

	/**
	 * Adds some scroll.
	 *
	 * @param scrollAmount Scroll amount to add
	 */
	final protected void addScroll(final float scrollAmount) {
		this.scroll += scrollAmount;
	}

	/**
	 * Returns the height ratio.
	 *
	 * @return Scroll.heightRatio
	 */
	final float getHeightRatio() {
		return this.heightRatio;
	}

}