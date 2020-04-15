package com.world.hud.status;

import com.objects.characters.Hero;
import engine.game.components.RenderedComponent;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import org.jetbrains.annotations.NotNull;

public class ManaBar extends StatusBar {

	/**
	 * Texture of mana bar's border when full mana.
	 */
	final public static Material BAR_BORDER_FULL = new Material(new Texture("hud/status/bar_border_mana_full"));

	/**
	 * Bar's border.
	 */
	final private RenderedComponent barBorder;

	/**
	 * Creates a new ManaBar instance.
	 *
	 * @param hero Hero to keep track of.
	 */
	protected ManaBar(final @NotNull Hero hero) {
		super("mana", hero);

		this.setPosition(new Vector2f(StatusBar.X_BETWEEN_BARS, 0));

		this.barBorder = new RenderedComponent(ManaBar.BAR_BORDER_FULL, StatusBar.WIDTH, StatusBar.HEIGHT, true);
		this.addComponent(this.barBorder);
	}

}