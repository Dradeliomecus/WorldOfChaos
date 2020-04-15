package engine.rendering;

import engine.math.Vector2f;
import engine.util.BufferUtil;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

final public class Mesh{

	/**
	 * Mesh's Vertex Buffer Objects.
	 */
	final private int vbo;

	/**
	 * Mesh's size.
	 */
	private int size;

	/**
	 * Creates a new Mesh instance.
	 */
	public Mesh() {
		this.vbo = glGenBuffers();
		this.size = 0;
	}

	@Override
	final protected void finalize() {
		try {
			super.finalize();
		} catch(final Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}

		glDeleteBuffers(this.vbo);
	}

	/**
	 * Sets the Mesh's vertices.
	 *
	 * @param vertices Vertex[] to set
	 */
	final public void setVertices(final @NotNull Vertex[] vertices) {
		this.size = vertices.length;

		glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(vertices), GL_STATIC_DRAW);
	}

	/**
	 * Draws the Mesh on the screen.
	 */
	final public void draw() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 8);

		glDrawArrays(GL_QUADS, 0, this.size);

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	}

	/**
	 * Creates and returns a basic mesh width * height.
	 *
	 * @param width Mesh's width
	 * @param height Mesh's height
	 * @return new Mesh
	 */
	public static @NotNull Mesh create(final float width, final float height) {
		final Mesh mesh = new Mesh();

		final Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector2f(0, 0), new Vector2f(0, 1)),
			new Vertex(new Vector2f(0, height), new Vector2f(0, 0)),
			new Vertex(new Vector2f(width, height), new Vector2f(1, 0)),
			new Vertex(new Vector2f(width, 0), new Vector2f(1, 1))
		};
		mesh.setVertices(vertices);

		return mesh;
	}

	/**
	 * Creates and returns a basic mesh width*height with an inverted y texture.
	 *
	 * @param width Mesh's width
	 * @param height Mesh's height
	 * @return new Mesh
	 */
	public static @NotNull Mesh createWithInvertedTexture(final float width, final float height) {
		final Mesh mesh = new Mesh();

		final Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector2f(0, 0), new Vector2f(0, 0)),
			new Vertex(new Vector2f(0, height), new Vector2f(0, 1)),
			new Vertex(new Vector2f(width, height), new Vector2f(1, 1)),
			new Vertex(new Vector2f(width, 0), new Vector2f(1, 0))
		};
		mesh.setVertices(vertices);

		return mesh;
	}

}