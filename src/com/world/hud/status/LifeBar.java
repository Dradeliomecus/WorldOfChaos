package com.world.hud.status;

import com.objects.characters.Hero;
import engine.game.components.RenderedComponent;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import org.jetbrains.annotations.NotNull;

public class LifeBar extends StatusBar {

	/**
	 * Texture of life bar's border when full life.
	 */
	final public static Material BAR_BORDER_FULL = new Material(new Texture("hud/status/bar_border_life_full"));

	/**
	 * Texture of life bar's border when not at full life.
	 */
	final public static Material BAR_BORDER_NOTFULL = new Material(new Texture("hud/status/bar_border_life_notfull"));

	/**
	 * Bar's border.
	 */
	final private RenderedComponent barBorder;

	/**
	 * Creates a new LifeBar instance.
	 *
	 * @param hero Hero to keep track of.
	 */
	protected LifeBar(final @NotNull Hero hero) {
		super("life", hero);

		this.barBorder = new RenderedComponent(LifeBar.BAR_BORDER_FULL, StatusBar.WIDTH, StatusBar.HEIGHT, true);
		this.addComponent(this.barBorder);
	}

	@Override
	public void update(final double delta) {
		super.update(delta);

		// TODO: If full life, set a border, otherwise set the other one.
	}

}