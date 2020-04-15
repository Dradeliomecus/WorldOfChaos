package engine.game.objects.map;

import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TileSet {

	/**
	 * TileSet's folder path.
	 */
	final private @NotNull String folder;

	/**
	 * HashMap that maps every character to a tile.
	 */
	final private @NotNull HashMap<Character, Tile> tiles;

	/**
	 * Creates a new TileSet instance.
	 *
	 * @param name TileSet's name
	 */
	public TileSet(final String name) {
		this.folder = "/tileSets/" + name;
		this.tiles = new HashMap<>();
	}

	/**
	 * Returns the TileSet's folder's path.
	 *
	 * @return TileSet.folder
	 */
	@Contract(pure = true)
	final protected String getFolderPath() {
		return this.folder;
	}

	/**
	 * Returns the TileSet's HashMap.
	 *
	 * @return TileSet.tiles
	 */
	@Contract(pure = true)
	private @NotNull HashMap<Character, Tile> getTiles() {
		return this.tiles;
	}

	/**
	 * Returns the Tile that corresponds to the character.
	 *
	 * @param character Tile's character
	 * @return TileSet.tiles[character]
	 */
	Tile getTile(final char character) {
		return this.getTiles().get(character);
	}

	/**
	 * Sets a new entry to what character corresponds to what name.
	 *
	 * @param character Tile's character in the map text file
	 * @param name Tile's name
	 * @param movementSpeed Tile's speed multiplicator
	 */
	final protected void setTile(final char character, final @NotNull String name, final float movementSpeed) {
		this.tiles.put(character, new Tile(name, movementSpeed, new Material(new Texture(this.getFolderPath() + "/" + name))));
	}

}