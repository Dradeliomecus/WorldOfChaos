package com.world.hud;

import com.objects.characters.Hero;
import engine.game.objects.GameObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class HUDCategory extends GameObject {

	/**
	 * Pointer to the parent HUD.
	 */
	private @NotNull HUD parentHUD;

	/**
	 * Creates a new HUDCategory instance.
	 *
	 * @param categoryName Category's name
	 * @param width Category's width on screen
	 * @param height Category's height on screen
	 * @param parentHUD Category's HUD
	 */
	public HUDCategory(final String categoryName, final float width, final float height, final @NotNull HUD parentHUD) {
		super("HUD category " + categoryName, width, height);

		this.parentHUD = parentHUD;
	}

	/**
	 * Returns the parent HUD.
	 *
	 * @return HUDCategory.parentHUD
	 */
	@Contract(pure = true)
	final protected @NotNull HUD getHUD() {
		return this.parentHUD;
	}

	/**
	 * Returns the Hero the HUD is attached to.
	 *
	 * @return HUDCategory.parentHUD.hero
	 */
	final protected @NotNull Hero getHero() {
		return this.getHUD().getHero();
	}

}