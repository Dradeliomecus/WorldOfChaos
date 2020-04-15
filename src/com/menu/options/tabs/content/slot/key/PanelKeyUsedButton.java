package com.menu.options.tabs.content.slot.key;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class PanelKeyUsedButton extends Button {

	/**
	 * PanelKeyUsedButton's width.
	 */
	final public static float WIDTH = 0.0775862069f * Window.getRatio();

	/**
	 * PanelKeyUsedButton's height.
	 */
	final public static float HEIGHT = 0.078125f;

	/**
	 * PanelKeyUsedButton's position.
	 */
	final public static Vector2f POS = new Vector2f(0.25862069f * Window.getRatio(), 0.046875f);

	/**
	 * PanelKeyUsedButton's style.
	 */
	final public static ButtonStyle STYLE = new ButtonStyle(
		new Material(new Texture("/menu/optionsPanel/tabs/keyAlreadyPickedPanel/button-ok")),
		new Material(new Texture("/menu/optionsPanel/tabs/keyAlreadyPickedPanel/button-ok-over")),
		new Material(new Texture("/menu/optionsPanel/tabs/keyAlreadyPickedPanel/button-ok-onclick")),
		PanelKeyUsedButton.WIDTH, PanelKeyUsedButton.HEIGHT
	);

	/**
	 * Pointer to the parent.
	 */
	final private PanelKeyUsed parent;

	/**
	 * Creates a new PanelKeUsedButton instance.
	 *
	 * @param parent Pointer to the parent
	 */
	PanelKeyUsedButton(final PanelKeyUsed parent) {
		super("PanelKeyUsed's ok button", PanelKeyUsedButton.STYLE);

		this.parent = parent;

		this.setPosition(PanelKeyUsedButton.POS);
		this.setDepth(-0.01f);
		this.setOverrideGlobalActivation((byte) 3);
	}

	@Override
	public void onClick() {
		this.getParentPanel().dismiss();
	}

	/**
	 * Returns the pointer to the parent (the panel window).
	 *
	 * @return PanelKeyUsedButton.parent
	 */
	private PanelKeyUsed getParentPanel() {
		return this.parent;
	}

}