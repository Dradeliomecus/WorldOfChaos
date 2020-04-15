package engine.game.objects.map;

import com.Options;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.util.Time;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class Zone extends GameObject {

	/**
	 * Zone's name.
	 */
	final private @NotNull String zoneName;

	/**
	 * Zone's x et y position (in number of tiles).
	 */
	final private int xPos, yPos;

	/**
	 * Zone's characters (each character correspond to a tile).
	 */
	final private @NotNull ArrayList<ArrayList<Character>> characters;

	/**
	 * Pointer to the map.
	 */
	final private @NotNull Map map;

	/**
	 * Creates a new Zone instance.
	 *
	 * @param name Zone's name
	 * @param xPos Zone's x position (in number of tiles from 0)
	 * @param yPos Zone's y position (in number of tiles from 0)
	 * @param width Zone's width (in number of tiles)
	 * @param height Zone's height (in number of tiles)
	 * @param map Map the zone belongs to
	 */
	Zone(final @NotNull String name, final int xPos, final int yPos, final int width, final int height, final @NotNull Map map) {
		super("Zone " + name, width * Options.TILE_SIZE, height * Options.TILE_SIZE);

		this.zoneName = name;
		this.xPos = xPos;
		this.yPos = yPos;
		this.characters = new ArrayList<>();
		this.map = map;
		this.setPosition(new Vector2f(Options.TILE_SIZE * xPos, Options.TILE_SIZE * yPos));
	}

	@Override
	public Zone init() {
		final long startTime = Time.getNanoTime();

		final ArrayList<ArrayList<Character>> characters1 = support.File.getCharactersFromTextFile("/media/map/" + this.map.getMapName() + "/" + this.getZoneName());

		for(int i = characters1.size() - 1; i >= 0; i--) { // Reversing the y axis since 0 is at the bottom.
			this.characters.add(characters1.get(i));
		}

		if(Options.DEBUG) System.out.println(this.getName() + " has been loaded in " + (Time.getNanoTime() - startTime) * Time.NANO_TO_MILLI + "ms.");
		return this;
	}

	/**
	 * Returns the zone's name.
	 *
	 * @return Zone.name
	 */
	@Contract(pure = true)
	final public @NotNull String getZoneName() {
		return this.zoneName;
	}

	/**
	 * Returns the character at (x;y) corresponding to the tile.
	 *
	 * @param x X position (in the map)
	 * @param y Y position (in the map)
	 * @return Zone.characters.get(y).get(x) or ' ' if out of bound
	 */
	final protected char getCharacterAt(final int x, final int y) {
		try {
			return this.characters.get(y - this.yPos).get(x - this.xPos);
		} catch(final IndexOutOfBoundsException e) {
			return ' ';
		}
	}

}