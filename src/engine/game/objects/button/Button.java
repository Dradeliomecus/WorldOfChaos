package engine.game.objects.button;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.util.Input;
import org.jetbrains.annotations.NotNull;

public class Button extends GameObject {

	/**
	 * Set to 0 when a window is suppose to disable every other buttons.
	 */
	public static byte GLOBAL_ACTIVATION = 0;

	/**
	 * Button's style (width, height, images etc).
	 */
	private @NotNull ButtonStyle style;

	/**
	 * Is the button on.
	 */
	private boolean isOn;

	/**
	 * Is the button selected.
	 */
	private boolean isSelected;

	/**
	 * Did the click start off the button.
	 */
	private boolean clickOffButton;

	/**
	 * Button's rendered component.
	 */
	private @NotNull RenderedComponent renderedComponent;

	/**
	 * If this value is lower than Button.GLOBAL_ACTIVATION, then
	 * the button will not do anything in the update method.
	 */
	private byte overrideGlobalActivation;

	/**
	 * If true, prevents the next click from calling onClick() method.
	 */
	private boolean preventNextClick;

	/**
	 * Creates a new Button instance.
	 *
	 * @param name Button's name to set
	 * @param style Button's style to set
	 */
	public Button(final @NotNull String name, final @NotNull ButtonStyle style) {
		super("Button " + name, style.getWidth(), style.getHeight());

		this.setStyle(style);
		this.setActive(true);
		this.setOverrideGlobalActivation((byte) 0);

		this.renderedComponent = new RenderedComponent(style.getBasicMaterial(), style.getWidth(), style.getHeight());
		this.addComponent(this.renderedComponent);
	}

	@Override
	public void update(final double delta) {
		super.update(delta);

		final boolean mouseOver = this.isMouseOver();
		final boolean clickedOffButton = this.hasClickedOffButton();

		if(Input.getMouseUp(Input.MOUSE_LEFT_BUTTON)) {
			this.setClickOffButton(false);
		}

		if(Button.GLOBAL_ACTIVATION > this.getOverrideGlobalActivation()) {
			return;
		}

		if(!this.isInScreen()) {
			return;
		}

		if(!mouseOver && Input.getMouseDown(Input.MOUSE_LEFT_BUTTON)) {
			this.setClickOffButton(true);
		}

		// Setting materials
		if(this.isOff()) {
			this.getRenderedComponent().setMaterial(this.getStyle().getOffMaterial());
			return;
		}

		if(this.isSelected()) {
			this.getRenderedComponent().setMaterial(this.getStyle().getSelectedMaterial());
			return;
		}

		if(!mouseOver) {
			this.getRenderedComponent().setMaterial(this.getStyle().getBasicMaterial());
			return;
		}

		if(Input.getMouse(Input.MOUSE_LEFT_BUTTON)) {
			if(clickedOffButton) {
				this.getRenderedComponent().setMaterial(this.getStyle().getBasicMaterial());
				return;
			}

			this.getRenderedComponent().setMaterial(this.getStyle().getClickMaterial());
			return;
		}

		this.getRenderedComponent().setMaterial(this.getStyle().getOverMaterial());


		// Check is the button has been clicked
		if(Input.getMouseUp(Input.MOUSE_LEFT_BUTTON) && !clickedOffButton) {
			if(this.doesPreventNextClick()) {
				this.setPreventNextClick(false);
			} else {
				this.onClick();
			}
		}
	}

	/**
	 * Whats happens when the Button has been clicked.
	 */
	protected void onClick() {

	}

	/**
	 * Returns the Button's style.
	 *
	 * @return Button.style
	 */
	private @NotNull ButtonStyle getStyle() {
		return this.style;
	}

	/**
	 * Returns if the Button is on.
	 *
	 * @return Button.on
	 */
	final protected boolean isOn() {
		return this.isOn;
	}

	/**
	 * Returns if the Button is off.
	 *
	 * @return !Button.on
	 */
	final protected boolean isOff() {
		return !this.isOn();
	}

	/**
	 * Returns whether the Button is selected.
	 *
	 * @return Button.isSelected
	 */
	final protected boolean isSelected() {
		return this.isSelected;
	}

	/**
	 * Returns if the click started off the Button.
	 *
	 * @return Button.clickOffButton
	 */
	final protected boolean hasClickedOffButton() {
		return this.clickOffButton;
	}

	/**
	 * Returns the Button's rendered component.
	 *
	 * @return Button.renderedComponent
	 */
	private @NotNull RenderedComponent getRenderedComponent() {
		return this.renderedComponent;
	}

	/**
	 * Returns if the Button overrides the global activation rule.
	 *
	 * @return Button.overrideGlobalActivation
	 */
	private byte getOverrideGlobalActivation() {
		return this.overrideGlobalActivation;
	}

	/**
	 * Returns whether the button will prevent the next click from calling onClick() method.
	 *
	 * @return Button.preventNextClick
	 */
	final public boolean doesPreventNextClick() {
		return this.preventNextClick;
	}

	/**
	 * Sets the Button's style.
	 *
	 * @param style Style to set
	 */
	private void setStyle(final @NotNull ButtonStyle style) {
		this.style = style;
	}

	/**
	 * Sets if the Button is active.
	 *
	 * @param isOn true = button on
	 */
	final public void setActive(final boolean isOn) {
		this.isOn = isOn;
	}

	/**
	 * Sets if the Button is selected.
	 *
	 * @param isSelected Value to put
	 */
	final public void setSelected(final boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * Sets if the click started off the Button.
	 *
	 * @param clicked true = click started off button
	 */
	private void setClickOffButton(final boolean clicked) {
		this.clickOffButton = clicked;
	}

	/**
	 * Sets the Button's rendered component.
	 *
	 * @param renderedComponent Rendered component to set
	 */
	final protected void setRenderedComponent(final @NotNull RenderedComponent renderedComponent) {
		this.removeComponent(this.renderedComponent);

		this.renderedComponent = renderedComponent;
		this.addComponent(this.renderedComponent);
	}

	/**
	 * Sets the override global activation rule.
	 *
	 * @param override "Power" of the override over the rule
	 */
	final protected void setOverrideGlobalActivation(final byte override) {
		this.overrideGlobalActivation = override;
	}

	/**
	 * If true, prevents the next click from calling the onClick() method.
	 *
	 * @param enabled True = prevents the click
	 */
	final protected void setPreventNextClick(final boolean enabled) {
		this.preventNextClick = enabled;
	}

}