package com.world.map;

import engine.game.objects.map.Map;

public class MainMap extends Map {

	/**
	 * Creates a new MainMap instance.
	 */
	public MainMap() {
		super("test", new MainTileSet());
	}

}