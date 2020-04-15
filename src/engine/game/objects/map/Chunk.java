package engine.game.objects.map;

import com.Options;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.Mesh;
import engine.rendering.Vertex;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

final class Chunk extends GameObject {

	/**
	 * Chunk's size (SIZE*SIZE tiles square).
	 */
	final public static int SIZE = 10;

	/**
	 * Chunk's length (in openGL measurements).
	 */
	final public static float LENGTH = Chunk.SIZE * Options.TILE_SIZE;

	/**
	 * Chunk's Mesh.
	 */
	final public static Mesh MESH = new Mesh();

	static {
		final Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector2f(0, Chunk.LENGTH), new Vector2f(0, 1)),
			new Vertex(new Vector2f(0, 0), new Vector2f(0, 0)),
			new Vertex(new Vector2f(Chunk.LENGTH, 0), new Vector2f(1, 0)),
			new Vertex(new Vector2f(Chunk.LENGTH, Chunk.LENGTH), new Vector2f(1, 1))
		};
		Chunk.MESH.setVertices(vertices);
	}

	/**
	 * Chunk's column.
	 */
	final private int x;

	/**
	 * Chunk's row.
	 */
	final private int y;

	/**
	 * Chunk's rendered component.
	 */
	final private @NotNull RenderedComponent renderedComponent;

	/**
	 * Creates a new Chunk instance.
	 *
	 * @param x Chunk's x position
	 * @param y Chunk's y position
	 */
	Chunk(final int x, final int y) {
		super("Chunk (" + x + ";" + y + ")", Chunk.LENGTH, Chunk.LENGTH);

		this.x = x;
		this.y = y;
		this.setPosition(new Vector2f(x * Chunk.LENGTH, y * Chunk.LENGTH));

		this.renderedComponent = new RenderedComponent(Chunk.MESH, new Material(new Texture("black1x1")), Chunk.LENGTH, Chunk.LENGTH);
		this.addComponent(this.renderedComponent);
	}

	/**
	 * Returns the Chunk's column number.
	 *
	 * @return Chunk.x
	 */
	@Contract(pure = true)
	final int getX() {
		return this.x;
	}

	/**
	 * Returns the Chunk's row number.
	 *
	 * @return Chunk.y
	 */
	@Contract(pure = true)
	final int getY() {
		return this.y;
	}

	/**
	 * Sets the Texture to the Chunk.
	 *
	 * @param texture Texture to set
	 */
	final void setTexture(final @NotNull Texture texture) {
		this.renderedComponent.getMaterial().setImage(texture);
	}

}