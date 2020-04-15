package com.menu.buttons;

import com.menu.MenuObject;
import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.math.Vector2f;
import engine.rendering.texture.Animation;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

public class NewGameButton extends Button {

	/**
	 * NewGameButton's basic texture.
	 */
	final private static Texture NORMAL_TEXTURE = new Texture("/menu/buttons/new-game");

	/**
	 * Reference to the parent's menu object.
	 */
	final private MenuObject menuObject;

	/**
	 * Create a new NewGameButton instance.
	 *
	 * @param menuObject Parent's menu object
	 */
	public NewGameButton(final MenuObject menuObject) {
		super("New Game", new ButtonStyle(
			new Material(NewGameButton.NORMAL_TEXTURE),
			new Material(new Animation(new Texture[] {new Texture("/menu/buttons/new-game-over-1"), new Texture("/menu/buttons/new-game-over-2")}, 1)),
			new Material(new Texture("/menu/buttons/new-game-onclick")),
			null,
			NORMAL_TEXTURE.getWidth() * 2 * Window.getRatio() / 232.0f, NORMAL_TEXTURE.getHeight() * 2 / 128.0f
		));

		this.menuObject = menuObject;
		this.setDepth(-0.1f);
		this.setPosition(new Vector2f(0.80f * Window.getRatio(), 0.875f));
	}

	@Override
	final protected void onClick() {
		this.getMenuObject().getGame().loadWorld();
	}

	/**
	 * Returns the reference to the parent's menu object.
	 *
	 * @return NewGameButton.menuObject
	 */
	private MenuObject getMenuObject() {
		return this.menuObject;
	}

}