package com.world.hud.minimap;

import com.world.hud.HUD;
import com.world.hud.HUDCategory;
import engine.game.components.RenderedComponent;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;
import org.jetbrains.annotations.NotNull;

public class Minimap extends HUDCategory {

	/**
	 * Minimap's width.
	 */
	final public static float WIDTH = 0.57936508f;

	/**
	 * Minimap's height.
	 */
	final public static float HEIGHT = 0.57936508f;

	/**
	 * Minimap's position.
	 */
	final public static Vector2f POS = new Vector2f(2.0f * Window.getRatio() - 0.63f, 2.0f - 0.63f);

	/**
	 * Minimap's background texture.
	 */
	final public static Material BACKGROUND = new Material(new Texture("hud/minimap/background"));

	/**
	 * Creates a new Minimap instance.
	 *
	 * @param parentHUD Minimap's HUD parent
	 */
	public Minimap(final @NotNull HUD parentHUD) {
		super("Minimap", Minimap.WIDTH, Minimap.HEIGHT, parentHUD);

		this.setPosition(Minimap.POS);

		this.addComponent(new RenderedComponent(Minimap.BACKGROUND, Minimap.WIDTH, Minimap.HEIGHT, true));
	}

}