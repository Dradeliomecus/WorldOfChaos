package com.menu.options;

import com.GameOptions;
import com.menu.MenuObject;
import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class OptionsPanelQuitButton extends Button {

	/**
	 * OptionsPanelQuitButton's x position.
	 */
	final static float X_POS = 1.405f * Window.getRatio();

	/**
	 * OptionsPanelQuitButton's y position.
	 */
	final static float Y_POS = 0.05f;

	/**
	 * OptionsPanelQuitButton's width.
	 */
	final static float WIDTH = 0.26f * Window.getRatio();

	/**
	 * OptionsPanelQuitButton's height.
	 */
	final static float HEIGHT = 0.15f;

	/**
	 * Menu object parent.
	 */
	final MenuObject menuObject;

	/**
	 * Creates a new OptionsPanelQuitButton instance.
	 *
	 * @param menuObject Menu object parent
	 */
	OptionsPanelQuitButton(final MenuObject menuObject) {
		super("Options Panel Quit", new ButtonStyle(
			new Material(new Texture("/menu/buttons/options-panel")),
			new Material(new Texture("/menu/buttons/options-panel-over")),
			new Material(new Texture("/menu/buttons/options-panel-onclick")),
			null,
			OptionsPanelQuitButton.WIDTH, OptionsPanelQuitButton.HEIGHT
		));

		this.menuObject = menuObject;
		this.setPosition(new Vector2f(OptionsPanelQuitButton.X_POS, OptionsPanelQuitButton.Y_POS));
		this.setDepth(-0.05f);
		this.setOverrideGlobalActivation((byte) 1);
	}

	@Override
	final public void onClick() {
		GameOptions.saveOptions(); // TODO: Implement the "Saving..." display before saving the options (or don't, discuss it with Gwenael, save is really quick)

		this.menuObject.removeOptionsPanel();
	}

}