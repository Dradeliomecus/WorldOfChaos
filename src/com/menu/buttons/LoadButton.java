package com.menu.buttons;

import com.menu.MenuObject;
import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.math.Vector2f;
import engine.rendering.texture.Animation;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

public class LoadButton extends Button {

	/**
	 * LoadButton's basic texture.
	 */
	final private static Texture NORMAL_TEXTURE = new Texture("/menu/buttons/load");

	/**
	 * Reference to the parent's menu object.
	 */
	final private MenuObject menuObject;

	/**
	 * Create a new LoadButton instance.
	 *
	 * @param menuObject Parent's menu object
	 */
	public LoadButton(final MenuObject menuObject) {
		super("Load", new ButtonStyle(
			new Material(LoadButton.NORMAL_TEXTURE),
			new Material(new Animation(new Texture[] {new Texture("/menu/buttons/load-over-1"), new Texture("/menu/buttons/load-over-2")}, 1)),
			new Material(new Texture("/menu/buttons/load-onclick")),
			new Material(new Texture("/menu/buttons/load-off")),
			NORMAL_TEXTURE.getWidth() * 2 * Window.getRatio() / 232.0f, NORMAL_TEXTURE.getHeight() * 2 / 128.0f
		));

		this.menuObject = menuObject;
		this.setDepth(-0.1f);
		this.setPosition(new Vector2f(0.80f * Window.getRatio(), 0.61f));
		this.setActive(support.File.hasFolders("/media/saves"));
	}

	@Override
	final protected void onClick() {
		// TODO
	}

	/**
	 * Returns the reference to the parent's menu object.
	 *
	 * @return ContinueButton.menuObject
	 */
	private MenuObject getMenuObject() {
		return this.menuObject;
	}

}