package engine.game.objects.button.selector;

import engine.game.objects.GameObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Selector extends GameObject {

	/**
	 * Selector's left selector.
	 */
	private @Nullable LeftSelector leftSelector;

	/**
	 * Selector's right selector.
	 */
	private @Nullable RightSelector rightSelector;

	/**
	 * Selector's display.
	 */
	final private @NotNull SelectorDisplay selectorDisplay;

	/**
	 * Selector's index.
	 */
	private int index;

	/**
	 * Creates a new Selector instance.
	 *
	 * @param name Selector's name
	 * @param selectorDisplay Selector's display to set
	 * @param width Selector's width
	 * @param height Selector's height
	 * @param index Selector's index
	 */
	public Selector(final @NotNull String name, final @NotNull SelectorDisplay selectorDisplay, final float width, final float height, final int index) {
		super("Selector " + name, width, height);

		this.selectorDisplay = selectorDisplay;
		this.addChild(selectorDisplay);
		this.setIndex(index);
	}

	/**
	 * Returns the Selector's left selector.
	 *
	 * @return Selector.leftSelector
	 */
	final protected @Nullable LeftSelector getLeftSelector() {
		return this.leftSelector;
	}

	/**
	 * Returns the Selector's right selector.
	 *
	 * @return Selector.rightSelector
	 */
	final protected @Nullable RightSelector getRightSelector() {
		return this.rightSelector;
	}

	/**
	 * Returns the Selector's display.
	 *
	 * @return Selector.selectorDisplay
	 */
	final protected @NotNull SelectorDisplay getSelectorDisplay() {
		return this.selectorDisplay;
	}

	/**
	 * Returns the Selector's index.
	 *
	 * @return Selector.index
	 */
	final public int getIndex() {
		return this.index;
	}

	/**
	 * Sets the Selector's left selector.
	 *
	 * @param leftSelector Left selector to set
	 */
	final protected void setLeftSelector(final @NotNull LeftSelector leftSelector) {
		if(this.getLeftSelector() != null) {
			this.removeChild(this.getLeftSelector());
		}

		if(this.getIndex() == 0) {
			leftSelector.setActive(false);
		} else {
			leftSelector.setActive(true);
		}

		this.addChild(leftSelector);
		this.leftSelector = leftSelector;
	}

	/**
	 * Sets the Selector's right selector.
	 *
	 * @param rightSelector Right selector to set
	 */
	final protected void setRightSelector(final @NotNull RightSelector rightSelector) {
		if(this.getRightSelector() != null) {
			this.removeChild(this.getRightSelector());
		}

		if(this.getIndex() == this.getSelectorDisplay().getMaterials().length - 1) {
			rightSelector.setActive(false);
		} else {
			rightSelector.setActive(true);
		}

		this.addChild(rightSelector);
		this.rightSelector = rightSelector;
	}

	/**
	 * Sets the Selector's index.
	 *
	 * @param index Index to set
	 */
	final protected void setIndex(final int index) {
		if(index < 0 || index >= this.getSelectorDisplay().getMaterials().length) {
			System.err.println("Error, index is not in range for the rendered components.");
			System.err.println("Index chosen: " + index + " ; length: " + this.getSelectorDisplay().getMaterials().length);
			new Exception().printStackTrace();
			System.exit(1);
		}

		if(this.getLeftSelector() != null) {
			this.getLeftSelector().setActive(true);
		}
		if(this.getRightSelector() != null) {
			this.getRightSelector().setActive(true);
		}

		if(index == 0 && this.getLeftSelector() != null) {
			this.getLeftSelector().setActive(false);
		} else if(index == this.getSelectorDisplay().getMaterials().length - 1 && this.getRightSelector() != null) {
			this.getRightSelector().setActive(false);
		}

		this.getSelectorDisplay().setIndex(index);
		this.index = index;
	}

}