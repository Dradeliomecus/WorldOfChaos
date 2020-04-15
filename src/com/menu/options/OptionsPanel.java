package com.menu.options;

import com.menu.MenuObject;
import com.menu.options.tabs.OptionsTabs;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

public class OptionsPanel extends GameObject {

	/**
	 * Panel's width.
	 */
	final public static float WIDTH = 1.74138f * Window.getRatio();

	/**
	 * Panel's height.
	 */
	final public static float HEIGHT = 1.6875f;

	/**
	 * Creates a new OptionsPanel instance.
	 *
	 * @param menuObject Menu object's parent
	 */
	public OptionsPanel(final MenuObject menuObject) {
		super("Options panel", OptionsPanel.WIDTH, OptionsPanel.HEIGHT);

		this.setPosition(new Vector2f((2 * Window.getRatio() - OptionsPanel.WIDTH) / 2, (2 - OptionsPanel.HEIGHT) / 2));
		this.setDepth(-0.2f);

		final Material material = new Material(new Texture("/menu/optionsPanel/background"));
		final RenderedComponent renderedComponent = new RenderedComponent(material, OptionsPanel.WIDTH, OptionsPanel.HEIGHT);
		this.addComponent(renderedComponent);

		final OptionsTabs tabs = new OptionsTabs();
		this.addChild(tabs);

		final OptionsPanelQuitButton quitButton = new OptionsPanelQuitButton(menuObject);
		this.addChild(quitButton);
	}

}