package engine.util;

import engine.math.Matrix4f;
import engine.rendering.Vertex;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

final public class BufferUtil {

	/**
	 * Creates a ByteBuffer.
	 *
	 * @param size Buffer's size
	 * @return ByteBuffer with the correct size
	 */
	public static ByteBuffer createByteBuffer(final int size) {
		return BufferUtils.createByteBuffer(size);
	}

	/**
	 * Creates a FloatBuffer.
	 *
	 * @param size Buffer's size
	 * @return FloatBuffer with the correct size
	 */
	public static FloatBuffer createFloatBuffer(final int size) {
		return BufferUtils.createFloatBuffer(size);
	}

	/**
	 * Creates a flipped FloatBuffer for vertices.
	 *
	 * @param vertices Vertex[] to put in the buffer
	 * @return Flipped FloatBuffer with correct data
	 */
	public static @NotNull FloatBuffer createFlippedBuffer(final @NotNull Vertex[] vertices) {
		final FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);

		for(final Vertex vertex : vertices) {
			buffer.put(vertex.getPos().getX());
			buffer.put(vertex.getPos().getY());
			buffer.put(vertex.getTextureCoords().getX());
			buffer.put(vertex.getTextureCoords().getY());
			/*buffer.put(vertex.getTextureCoords().getX());
			buffer.put(vertex.getTextureCoords().getY());
			buffer.put(vertex.getPos().getX());
			buffer.put(vertex.getPos().getY());*/
			//TODO: Why is it the inverse here?
		}

		buffer.flip();

		return buffer;
	}

	/**
	 * Creates a flipped FloatBuffer for a Matrix4f.
	 *
	 * @param matrix Matrix4f to put in the buffer
	 * @return Flipped FloatBuffer with correct data
	 */
	public static @NotNull FloatBuffer createFlippedBuffer(final Matrix4f matrix) {
		final FloatBuffer buffer = createFloatBuffer(16);

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				buffer.put(matrix.get(i, j));
			}
		}

		buffer.flip();

		return buffer;
	}

	/**
	 * Creates a flipped ByteBuffer for an image.
	 *
	 * @param width Image width (in px)
	 * @param height Image height (in px)
	 * @param hasAlpha Does the image contains transparency
	 * @param pixels Image pixels
	 * @return Flipped ByteBuffer with correct data
	 */
	public static @NotNull ByteBuffer createFlippedBuffer(final int width, final int height, final boolean hasAlpha, final int[] pixels) {
		final ByteBuffer buffer = BufferUtil.createByteBuffer(width * height * 4);

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				final int pixel = pixels[i * width + j];

				buffer.put((byte)((pixel >> 16) & 0xFF));
				buffer.put((byte)((pixel >> 8)  & 0xFF));
				buffer.put((byte)((pixel     )  & 0xFF));
				if(hasAlpha) {
					buffer.put((byte)((pixel >> 24) & 0xFF));
				} else {
					buffer.put((byte) 0xFF);
				}
			}
		}

		buffer.flip();

		return buffer;
	}

	/**
	 * Creates a flipped ByteBuffer for an image.
	 *
	 * @param filename Image filename
	 * @return Flipped ByteBuffer with correct data
	 */
	public static @NotNull ByteBuffer createFlippedBuffer(final String filename) {
		final BufferedImage img = support.File.getImage(filename);
		if(img == null) {
			System.out.println(filename);
			System.exit(1);
		}
		final boolean hasAlpha = img.getColorModel().hasAlpha();

		final int width = img.getWidth();
		final int height = img.getHeight();

		final int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);

		return BufferUtil.createFlippedBuffer(width, height, hasAlpha, pixels);
	}

}