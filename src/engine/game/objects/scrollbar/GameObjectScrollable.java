package engine.game.objects.scrollbar;

import engine.game.objects.GameObject;
import engine.math.Vector2f;

public class GameObjectScrollable extends GameObject {

	/**
	 * GameObject's total width.
	 */
	private float totalWidth;

	/**
	 * GameObject's total height.
	 */
	private float totalHeight;

	/**
	 * GameObject's scrollbar.
	 */
	private Scrollbar scrollbar;

	/**
	 * Creates a new GameObjectScrollable instance.
	 *
	 * @param name Object's name
	 * @param width Object's width
	 * @param height Object's height
	 * @param totalWidth Object's total width
	 * @param totalHeight Object's total height
	 */
	public GameObjectScrollable(final String name, final float width, final float height, final float totalWidth, final float totalHeight) {
		super("Scrollable " + name, width, height);

		if(totalWidth != 0 && totalHeight != 0) {
			System.err.println("Error, there must be at least one value equals to 0,");
			System.err.println("totalWidth: " + totalWidth + " ; totalHeight: " + totalHeight);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.totalWidth = totalWidth;
		this.totalHeight = totalHeight;
	}

	/**
	 * Scrolls the GameObject
	 * A positive scrollAmount's value is up/left
	 * A negative scrollAmount's value is down/right.
	 *
	 * @param scrollAmount Amount to scroll
	 */
	final protected void scroll(final float scrollAmount) {
		final Vector2f scroll = new Vector2f(this.getTotalWidth() == 0 ? 0 : scrollAmount, this.getTotalHeight() == 0 ? 0 : scrollAmount);

		for(final GameObject child : this.getChildren()) {
			if(child instanceof Scrollbar) {
				continue;
			}

			child.setPosition(child.getObjectPosition().add(scroll));
		}
	}

	/**
	 * Returns the object's total width.
	 *
	 * @return GameObjectScrollable.totalWidth
	 */
	final protected float getTotalWidth() {
		return this.totalWidth;
	}

	/**
	 * Returns the object's total height.
	 *
	 * @return GameObjectScrollable.totalHeight
	 */
	final protected float getTotalHeight() {
		return this.totalHeight;
	}

	/**
	 * Returns the object's scrollbar.
	 *
	 * @return GameObjectScrollable.scrollbar
	 */
	private Scrollbar getScrollbar() {
		return this.scrollbar;
	}

	/**
	 * Sets the object's total width.
	 *
	 * @param totalWidth Width to set
	 */
	final protected void setTotalWidth(final float totalWidth) {
		if(totalWidth != 0 && this.getTotalHeight() != 0) {
			System.err.println("Error, there must be at least one value equals to 0,");
			System.err.println("totalWidth to set: " + totalWidth + " ; totalHeight: " + this.getTotalHeight());
			throw new IllegalArgumentException();
		}

		this.totalWidth = totalWidth;
	}

	/**
	 * Sets the object's total height.
	 *
	 * @param totalHeight Height to set
	 */
	final protected void setTotalHeight(final float totalHeight) {
		if(this.getTotalWidth() != 0 && totalHeight != 0) {
			System.err.println("Error, there must be at least one value equals to 0,");
			System.err.println("totalWidth: " + this.getTotalWidth() + " ; totalHeight to set: " + totalHeight);
			throw new IllegalArgumentException();
		}

		this.totalHeight = totalHeight;
	}

	/**
	 * Sets the object's scroll bar.
	 *
	 * @param scrollbar Scroll bar to set
	 */
	final protected void setScrollbar(final Scrollbar scrollbar) {
		if(this.getScrollbar() != null) {
			this.removeChild(this.getScrollbar());
		}

		this.scrollbar = scrollbar;
		this.addChild(scrollbar);
	}

}