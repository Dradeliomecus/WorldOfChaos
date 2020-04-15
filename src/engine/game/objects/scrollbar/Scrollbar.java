package engine.game.objects.scrollbar;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.util.Input;

public class Scrollbar extends Button {

	/**
	 * Scroll multiplicator (one scroll (on my mouse) is 120).
	 * TODO: Check if I didn't screwed up top/bottom (this value should not be negative)
	 */
	final public static float SCROLL_MULTIPLICATOR = -1.0f / 1200.0f;

	/**
	 * Parent that the scrollbar refers to.
	 */
	final private GameObjectScrollable parent;

	/**
	 * Scrollbar's scroll.
	 */
	final private Scroll scroll;

	/**
	 * Creates a new Scrollbar instance.
	 *
	 * @param name Scrollbar's name
	 * @param style Scrollbar's style (background)
	 * @param scroll Scrollbar's scroll
	 * @param parent Parent that the scrollbar's refers to
	 */
	public Scrollbar(final String name, final ButtonStyle style, final Scroll scroll, final GameObjectScrollable parent) {
		super("Scrollbar " + name, style);

		this.scroll = scroll;
		this.parent = parent;

		if(this.getScroll().getHeightRatio() == 1) {
			this.setActive(false);
		} else {
			this.setActive(true);
		}

		this.addChild(scroll);
	}

	@Override
	public void update(final double delta) {
		super.update(delta);

		final float scrollAmount = Input.getScrollAmount() * Scrollbar.SCROLL_MULTIPLICATOR;

		if((scrollAmount != 0) && (scrollAmount + this.getScroll().getScroll() >= -0.000001f) && (scrollAmount + this.getScroll().getScroll() + this.getParent().getHeight() <= this.getParent().getTotalHeight() + 0.000001f)) {
			this.getParent().scroll(scrollAmount);
			this.getScroll().addScroll(scrollAmount);
		}
	}

	/**
	 * Returns the Scrollbar's scroll.
	 *
	 * @return Scrollbar.scroll
	 */
	private Scroll getScroll() {
		return this.scroll;
	}

	/**
	 * Returns the Scrollbar's parent.
	 *
	 * @return Scrollbar.parent
	 */
	private GameObjectScrollable getParent() {
		return this.parent;
	}

}