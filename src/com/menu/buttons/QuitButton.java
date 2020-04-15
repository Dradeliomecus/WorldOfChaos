package com.menu.buttons;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.math.Vector2f;
import engine.rendering.texture.Animation;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

public class QuitButton extends Button {

	/**
	 * QuitButton's basic texture.
	 */
	final private static Texture NORMAL_TEXTURE = new Texture("/menu/buttons/quit");

	/**
	 * Create a new QuitButton instance.
	 */
	public QuitButton() {
		super("Quit", new ButtonStyle(
			new Material(QuitButton.NORMAL_TEXTURE),
			new Material(new Animation(new Texture[] {new Texture("/menu/buttons/quit-over-1"), new Texture("/menu/buttons/quit-over-2")}, 1)),
			new Material(new Texture("/menu/buttons/quit-onclick")),
			null,
			NORMAL_TEXTURE.getWidth() * 2 * Window.getRatio() / 232.0f, NORMAL_TEXTURE.getHeight() * 2 / 128.0f
		));

		this.setDepth(-0.1f);
		this.setPosition(new Vector2f(0.80f * Window.getRatio(), 0.234f));
	}

	@Override
	final protected void onClick() {
		this.getCoreEngine().stop();
	}

}