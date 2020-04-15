package com.world.hud;

import com.objects.characters.Hero;
import com.world.hud.chat.ChatBox;
import com.world.hud.minimap.Minimap;
import com.world.hud.status.HUDStatus;
import engine.game.objects.GameObject;
import engine.util.Window;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class HUD extends GameObject {

	/**
	 * Pointer to the hero.
	 */
	final private @NotNull Hero hero;

	/**
	 * HUD's minimap.
	 */
	final private @NotNull Minimap minimap;

	/**
	 * HUD's status.
	 */
	final private @NotNull HUDStatus status;

	/**
	 * HUD's chat box.
	 */
	final private @NotNull ChatBox chatbox;

	/**
	 * Creates a new HUD instance.
	 *
	 * @param hero Pointer to the hero
	 */
	public HUD(final @NotNull Hero hero) {
		super("HUD", 2.0f * Window.getRatio(), 2.0f);

		this.hero = hero;
		this.setDepth(-1.0f);

		this.minimap = new Minimap(this);
		this.addChild(this.minimap);
		this.minimap.init();

		this.status = new HUDStatus(this);
		this.addChild(this.status);
		this.status.init();

		this.chatbox = new ChatBox(this);
		this.addChild(this.chatbox);
		this.chatbox.init();

		// Chatbox : 294*62(mini-height)px / (1060 width, 756 height)
		// Bar : 34*339px
		// Mana bar 55px to the right of life bar
	}

	@Override
	public void update(final double delta) {
		super.update(delta);

		this.setPosition(this.getHero().getPosition().sub(1.0f * Window.getRatio(), 1.0f));
	}

	/**
	 * Returns the pointer to the Hero.
	 *
	 * @return HUD.hero
	 */
	@Contract(pure = true)
	final protected @NotNull Hero getHero() {
		return this.hero;
	}

	/**
	 * Return's the HUD's minimap.
	 *
	 * @return HUD.minimap
	 */
	@Contract(pure = true)
	final protected @NotNull Minimap getMinimap() {
		return this.minimap;
	}

	/**
	 * Return's the HUD's status.
	 *
	 * @return HUD.status
	 */
	@Contract(pure = true)
	final protected @NotNull HUDStatus getStatus() {
		return this.status;
	}

}