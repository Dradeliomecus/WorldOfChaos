package engine.game.objects.button.slideBar;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.math.Vector2f;
import engine.util.Input;
import engine.util.Units;

public class Bar extends Button {

    /**
     * SlideBar the Bar refers to.
     */
    final private SlideBar parent;

    /**
     * SlideBar's slide.
     */
    private Slide slide;

    /**
     * Creates a new Bar instance.
     *
     * @param name Bar's name
     * @param buttonStyle Bar's button style
     * @param parent SlideBar the Bar refers to
     */
    public Bar(final String name, final ButtonStyle buttonStyle, final SlideBar parent) {
        super(name, buttonStyle);

        this.parent = parent;
    }

    @Override
    public void update(final double delta) {
        super.update(delta);

        if(Input.getMouse(Input.MOUSE_LEFT_BUTTON) && !this.hasClickedOffButton() && this.isMouseOver()) {
            final float mousePositionX = Units.pixelsToOpenGL(Input.getMousePosition()).sub(this.getPositionOnScreen()).getX();
	        final float valueFrom0to1 = mousePositionX / this.getWidth();
	        final int value = Math.round(valueFrom0to1 * (this.getSlideBarParent().getMaxValue() - this.getSlideBarParent().getMinValue()) + this.getSlideBarParent().getMinValue());

	        this.getSlideBarParent().setValue(value);
        }
    }

	/**
	 * Returns the Bar's slide.
	 *
	 * @return Bar.slide
	 */
	final protected Slide getSlide() {
		return this.slide;
	}

    /**
     * Returns the Slide Bar the Bar refers to.
     *
     * @return Bar.parent
     */
    final protected SlideBar getSlideBarParent() {
        return this.parent;
    }

	/**
	 * Sets the SlideBar's slide.
	 *
	 * @param slide Slide to set
	 */
	final protected void setSlide(final Slide slide) {
		if(this.getSlide() != null) {
			this.removeChild(this.getSlide());
		}

		this.slide = slide;

		final float xPos = this.getWidth() * ((float)(this.getSlideBarParent().getValue() - this.getSlideBarParent().getMinValue()) / (float)(this.getSlideBarParent().getMaxValue() - this.getSlideBarParent().getMinValue()));
		this.getSlide().setPosition(new Vector2f(xPos - slide.getWidth() / 2.0f, 0));
		this.addChild(slide);
	}

}