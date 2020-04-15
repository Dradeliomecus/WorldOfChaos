package engine.game.objects.button.slideBar;

import engine.game.objects.text.Font;
import engine.game.objects.text.Text;
import engine.math.Vector2f;

public class SlideBarDisplay extends Text {

    /**
     * Is the display a percentage or not.
     */
    private boolean isPercentage;

    /**
     * Creates a new SlideBarDisplay instance.
     *
     * @param value Value to begin with
     * @param font Font to use
     * @param isPercentage Is the display a percentage
     * @param width SlideBarDisplay's width
     * @param height SlideBarDisplay's height
     * @param pos SlideBarDisplay's position
     */
    public SlideBarDisplay(final int value, final Font font, final boolean isPercentage, final float width, final float height, final Vector2f pos) {
        super(value + (isPercentage ? " %" : ""), font, width, height);

	    this.setPosition(pos);
	    this.setIsPercentage(isPercentage);
    }

    @Override
    public SlideBarDisplay init() {
        this.print();

        return this;
    }

	/**
	 * Sets the SlideBarDisplay's value.
     *
     * @param value Value to set
     */
    final void setValue(final int value) {
        final String text = value + (this.isPercentage() ? " %" : "");

        this.setText(text);
        this.print();
    }

    /**
     * Returns whether the display is in percentage or not.
     *
     * @return SideBarDisplay.isPercentage
     */
    final public boolean isPercentage() {
        return this.isPercentage;
    }

    /**
     * Sets whether the display is in percentage or not.
     *
     * @param isPercentage true = is percentage
     */
    final protected void setIsPercentage(final boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

}