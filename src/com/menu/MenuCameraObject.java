package com.menu;

import engine.game.components.Camera;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.util.Window;

final class MenuCameraObject extends GameObject {

	/**
	 * Create a new CameraObject instance.
	 *
	 * @param camera Camera to set
	 */
	public MenuCameraObject(final Camera camera) {
		super("Menu Camera Object", Float.MAX_VALUE, Float.MAX_VALUE);

		this.setPosition(new Vector2f(Window.getRatio(), 1));
		this.setDepth(-1 * MenuObject.DEPTH);

		this.addComponent(camera);
	}

}