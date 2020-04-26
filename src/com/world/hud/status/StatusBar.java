package com.world.hud.status;

import com.objects.characters.Hero;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class StatusBar extends GameObject {

	/**
	 * Bar's width.
	 */
	final public static float WIDTH = 0.08994709f;

	/**
	 * Bar's height.
	 */
	final public static float HEIGHT = 0.8968253968f;

	/**
	 * X position between 2 bars.
	 */
	final public static float X_BETWEEN_BARS = 0.1455026455f;

	/**
	 * Bar's background texture.
	 */
	final public static Material BAR_BACKGROUND = new Material(new Texture("hud/status/bar_background"));

	/**
	 * Pointer's to the hero.
	 */
	final private Hero hero;

	/**
	 * Creates a new StatusBar instance.
	 *
	 * @param name Bar's name
	 * @param hero Hero to keep track of
	 */
	protected StatusBar(final @NotNull String name, final @NotNull Hero hero) {
		super("HUD status bar [" + name + "]", StatusBar.WIDTH, StatusBar.HEIGHT);

		this.hero = hero;

		this.addComponent(new RenderedComponent(StatusBar.BAR_BACKGROUND, StatusBar.WIDTH, StatusBar.HEIGHT));
	}

	/**
	 * Returns the pointer to the hero.
	 *
	 * @return StatusBar.hero
	 */
	@Contract(pure = true)
	final protected Hero getHero() {
		return this.hero;
	}

}