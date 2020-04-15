package engine.game.objects.button.slideBar;

import engine.game.objects.GameObject;
import engine.math.Vector2f;
import support.SlideValues;

public class SlideBar extends GameObject {

    /**
     * SlideBar's display.
     */
    final private SlideBarDisplay slideBarDisplay;

	/**
	 * SlideBar's bar.
	 */
    private Bar bar;

	/**
	 * SlideBar's values.
	 */
	final private SlideValues values;

    /**
     * Creates a new SlideBar instance.
     *
     * @param name SlideBar's name
     * @param slideBarDisplay SlideBar's display to set
     * @param width SlideBar's width
     * @param height SlideBar's height
     * @param values SlideBar's values
     */
    public SlideBar(final String name, final SlideBarDisplay slideBarDisplay, final float width, final float height, final SlideValues values) {
        super("SlideBar " + name, width, height);

        this.slideBarDisplay = slideBarDisplay;
        this.addChild(slideBarDisplay);
	    this.values = values;
    }

    /**
     * Returns the SlideBar's display.
     *
     * @return SlideBar.slideBarDisplay
     */
    final protected SlideBarDisplay getSlideBarDisplay() {
        return this.slideBarDisplay;
    }

	/**
	 * Returns the SlideBar's bar.
	 *
	 * @return SlideBar.bar
	 */
	final protected Bar getBar() {
		return this.bar;
	}

	/**
	 * Returns the SlideBar's slide.
	 *
	 * @return SlideBar.slide
	 */
	final protected Slide getSlide() {
		if(this.getBar() == null) {
			return null;
		}

		return this.getBar().getSlide();
	}

	/**
	 * Returns the SlideBar's SlideValues.
	 *
	 * @return SlideBar.SlideValues
	 */
	private SlideValues getValues() {
		return this.values;
	}

    /**
     * Returns the SlideBar's minimum value.
     *
     * @return SlideBar.minValue
     */
    final public int getMinValue() {
        return this.getValues().getMinValue();
    }

    /**
     * Returns the SlideBar's maximum value.
     *
     * @return SlideBar.maxValue
     */
    final public int getMaxValue() {
        return this.getValues().getMaxValue();
    }

    /**
     * Returns the SlideBar's value.
     *
     * @return SlideBar.value
     */
    final public int getValue() {
        return this.getValues().getValue();
    }

	/**
     * Sets the SlideBar's bar.
     *
     * @param bar Bar to set
     */
    final protected void setBar(final Bar bar) {
        if(this.getBar() != null) {
            this.removeChild(this.getBar());
        }

        this.bar = bar;
        this.addChild(bar);
    }

    /**
     * Sets the SlideBar's minimum value.
     *
     * @param minValue Minimum value to set
     */
    final protected void setMinValue(final int minValue) {
        this.getValues().setMinValue(minValue);
    }

    /**
     * Sets the SlideBar's maximum value.
     *
     * @param maxValue Maximum value to set
     */
    final protected void setMaxValue(final int maxValue) {
        this.getValues().setMaxValue(maxValue);
    }

    /**
     * Sets the SlideBar's value.
     *
     * @param value Value to set
     */
    protected void setValue(final int value) {
	    if(value < this.getMinValue() || value > this.getMaxValue()) {
		    System.err.println("Error: Value in not in range");
		    System.err.println("Value: " + value + " ; range: [" + this.getMinValue() + " , " + this.getMaxValue() + "]");
		    new Exception().printStackTrace();
		    System.exit(1);
	    }

        this.getValues().setValue(value);

	    if(this.getSlide() != null) {
			final float xPos = this.getBar().getWidth() * ((float)(this.getValue() - this.getMinValue()) / (float)(this.getMaxValue() - this.getMinValue()));
		    this.getSlide().setPosition(new Vector2f(xPos - this.getSlide().getWidth() / 2, 0));
	    }

	    this.getSlideBarDisplay().setValue(value);
    }

}