package com.world.map;

import engine.game.objects.map.TileSet;

class MainTileSet extends TileSet {

	/**
	 * Creates a new MainTileSet instance.
	 */
	MainTileSet() {
		super("main");

		this.setTile('g', "grass", 1.0f);
		this.setTile('s', "sand", 0.65f); // TODO: Temporary 0.6 for debugging, we need to find a real value.
		this.setTile('w', "water", -1.0f);
		this.setTile('W', "highWater", 0.0f);
	}

}