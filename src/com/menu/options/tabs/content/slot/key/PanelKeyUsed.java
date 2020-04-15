package com.menu.options.tabs.content.slot.key;

import com.menu.options.tabs.content.TabsContent;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.game.objects.button.Button;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class PanelKeyUsed extends GameObject {

	/**
	 * PanelKeyUsed's width.
	 */
	final public static float WIDTH = 0.58620689655f * Window.getRatio();

	/**
	 * PanelKeyUsed's height.
	 */
	final public static float HEIGHT = 0.21875f;

	/**
	 * PanelKeyUsed's position.
	 */
	final public static Vector2f POS = new Vector2f((TabsContent.WIDTH - PanelKeyUsed.WIDTH) / 2, (TabsContent.HEIGHT - PanelKeyUsed.HEIGHT) / 2);

	/**
	 * Pointer to the parent.
	 */
	final private TabsContent parent;

	/**
	 * PanelKeyUsed's background.
	 */
	final public static RenderedComponent BACKGROUND = new RenderedComponent(
		new Material(new Texture("/menu/optionsPanel/tabs/keyAlreadyPickedPanel/background")),
		PanelKeyUsed.WIDTH, PanelKeyUsed.HEIGHT
	);

	/**
	 * Creates a new PanelKeyUsed instance.
	 *
	 * @param parent Pointer to the parent
	 */
	PanelKeyUsed(final TabsContent parent) {
		super("Panel Key is already used", PanelKeyUsed.WIDTH, PanelKeyUsed.HEIGHT);

		this.parent = parent;

		this.setPosition(PanelKeyUsed.POS);
		this.setDepth(-0.3f);
		Button.GLOBAL_ACTIVATION = (byte) 3;

		this.addComponent(PanelKeyUsed.BACKGROUND);
		this.addChild(new PanelKeyUsedButton(this));
	}

	/**
	 * Gets rid of the window.
	 */
	final void dismiss() {
		Button.GLOBAL_ACTIVATION = (byte) 1;

		this.parent.removeChild(this);
	}

}