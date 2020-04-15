package engine.game.objects.map;

import com.Options;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.Mesh;
import engine.rendering.Vertex;
import engine.rendering.texture.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Tile extends GameObject {

	/**
	 * Tile's mesh (to avoid making multiple ones).
	 */
	final public static Mesh MESH = new Mesh();

	static {
		final Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector2f(0, 0), new Vector2f(0, 1)),
			new Vertex(new Vector2f(0, Options.TILE_SIZE), new Vector2f(0, 0)),
			new Vertex(new Vector2f(Options.TILE_SIZE, Options.TILE_SIZE), new Vector2f(1, 0)),
			new Vertex(new Vector2f(Options.TILE_SIZE, 0), new Vector2f(1, 1))
		};
		Tile.MESH.setVertices(vertices);
	}

	/**
	 * The speed multiplicator of someone walking on the tile.
	 * Negative means speed but swimming.
	 */
	final private float speedMultiplicator;

	/**
	 * Tile's rendered component.
	 */
	final private @NotNull RenderedComponent renderedComponent;

	/**
	 * Creates a new Tile instance.
	 *
	 * @param tileName Tile's name
	 * @param speedMultiplicator Speed multiplicator to set (0 = can't walk on)
	 * @param tileMaterial Tile's material
	 */
	protected Tile(final String tileName, final float speedMultiplicator, final Material tileMaterial) {
		super("Tile " + tileName, Options.TILE_SIZE, Options.TILE_SIZE);

		this.speedMultiplicator = speedMultiplicator;
		this.renderedComponent = new RenderedComponent(Tile.MESH, tileMaterial, Options.TILE_SIZE, Options.TILE_SIZE);

		this.addComponent(this.getRenderedComponent());
	}

	/**
	 * Returns the Tile's speed multiplicator.
	 *
	 * @return Tile.speedMultiplicator
	 */
	@Contract(pure = true)
	final public float getSpeedMultiplicator() {
		return this.speedMultiplicator;
	}

	/**
	 * Returns if we can walk on the tile.
	 *
	 * @return Tile.speedMultiplicator > 0
	 */
	@Contract(pure = true)
	final public boolean canWalkOn() {
		return this.getSpeedMultiplicator() > 0.0f;
	}

	/**
	 * Returns if we can swim in the tile.
	 *
	 * @return Tile.speedMulciplicator < 0
	 */
	@Contract(pure = true)
	final public boolean canSwimIn() {
		return this.getSpeedMultiplicator() < 0.0f;
	}

	/**
	 * Returns the Tile's rendered component.
	 *
	 * @return Tile.renderedComponent
	 */
	@Contract(pure = true)
	final @NotNull RenderedComponent getRenderedComponent() {
		return this.renderedComponent;
	}

}