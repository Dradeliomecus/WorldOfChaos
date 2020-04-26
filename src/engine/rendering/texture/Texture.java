package engine.rendering.texture;

import com.Options;
import engine.util.BufferUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_NONE;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawBuffer;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL30.GL_DEPTH_ATTACHMENT;
import static org.lwjgl.opengl.GL30.GL_DRAW_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_COMPLETE;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengl.GL30.glCheckFramebufferStatus;
import static org.lwjgl.opengl.GL30.glFramebufferTexture2D;
import static org.lwjgl.opengl.GL30.glGenFramebuffers;

final public class Texture extends Image {

	/**
	 * List of all the Textures loaded from a filename.
	 */
	final private static HashMap<String, TextureResource> loadedTextures = new HashMap<>();

	/**
	 * Texture's resource.
	 */
	private TextureResource resource;

	/**
	 * Texture's file name.
	 */
	private @Nullable String filename = null;

	/**
	 * Creates a new Texture instance.
	 *
	 * @param width Texture's width
	 * @param height Texture's height
	 * @param attachment Texture's attachment
	 */
	public Texture(final int width, final int height, final int attachment) {
		this.createTexture(Texture.create(width, height), attachment);
	}

	/**
	 * Creates a new Texture instance.
	 *
	 * @param width Texture's width
	 * @param height Texture's height
	 */
	public Texture(final int width, final int height) {
		this(width, height, GL_NONE);
	}

	/**
	 * Creates a new Texture instance.
	 *
	 * @param width Texture's width
	 * @param height Texture's height
	 * @param pixels Texture's pixels
	 * @param attachment Texture's attachment
	 */
	public Texture(final int width, final int height, final int[] pixels, final int attachment) {
		this.createTexture(Texture.generateTexture(width, height, true, pixels), attachment);
	}

	/**
	 * Creates a new Texture instance.
	 *
	 * @param width Texture's width
	 * @param height Texture's height
	 * @param pixels Texture's pixels
	 */
	public Texture(final int width, final int height, final int[] pixels) {
		this(width, height, pixels, GL_NONE);
	}

	/**
	 * Creates a new Texture instance.
	 *
	 * @param filename File name
	 * @param attachment Texture's attachment
	 */
	public Texture(final @NotNull String filename, final int attachment) {
		this.filename = filename;

		final TextureResource resource = Texture.loadedTextures.get(filename);

		if(resource != null) {
			this.resource = resource;
			resource.addReference();
		} else {
			this.createTexture(Texture.load(filename), attachment);
			Texture.loadedTextures.put(filename, this.resource);
		}
	}

	/**
	 * Creates a new Texture instance.
	 *
	 * @param filename File name
	 */
	public Texture(final @NotNull String filename) {
		this(filename, GL_NONE);
	}

	/**
	 * Creates a the parameters for the texture.
	 *
	 * @param params Texture's parameters: 0 = id, 1 = width, 2 = height
	 * @param attachment Texture's attachment
	 */
	private void createTexture(final @NotNull int[] params, int attachment) {
		if(params.length < 3) {
			System.err.println("Error: params has a length of " + params.length + ", 3 params are required.");
			new Exception().printStackTrace();
			System.exit(1);
		}

		final int id = params[0];
		final int width = params[1];
		final int height = params[2];

		int fbo = 0;

		if(attachment != GL_NONE) {
			fbo = glGenFramebuffers();
			glBindFramebuffer(GL_DRAW_FRAMEBUFFER, fbo);
			glFramebufferTexture2D(GL_DRAW_FRAMEBUFFER, attachment, GL_TEXTURE_2D, id, 0);

			if(fbo == 0) {
				this.resource = null;
				return;
			}

			glDrawBuffer(attachment == GL_DEPTH_ATTACHMENT ? GL_NONE : attachment);

			if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
				System.err.println("FrameBuffer creation failed.");
				System.err.println("This might be caused by the texture size that is too big:");
				System.err.println("Width: " + width + "px ; Height: " + height + "px");
				new Exception().printStackTrace();
				System.exit(1);
			}
		} else {
			attachment = GL_NONE;
		}

		this.resource = new TextureResource(id, fbo, width, height, attachment);
	}

	@Override
	final protected void finalize() {
		try {
			super.finalize();
		} catch(final Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}

		if(this.getResource().removeReference()) {
			if(this.getFilename() != null) {
				Texture.loadedTextures.remove(this.getFilename());
			}
		}
	}

	@Override
	@Contract(pure = true)
	final @NotNull Texture getTexture() {
		return this;
	}

	/**
	 * Binds the Texture to tell OpenGL to use it.
	 */
	final public void bind() {
		glBindTexture(GL_TEXTURE_2D, this.getID());
	}

	/**
	 * Binds the Texture to render into it.
	 */
	final public void bindAsRenderTarget() {
		glBindFramebuffer(GL_DRAW_FRAMEBUFFER, this.getFBO());
		glViewport(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Returns the Texture's resource.
	 *
	 * @return Texture.resource
	 */
	@Contract(pure = true)
	private TextureResource getResource() {
		return this.resource;
	}

	/**
	 * Returns the Texture's id.
	 *
	 * @return Texture.resource.id
	 */
	final public int getID() {
		return this.getResource().getID();
	}

	/**
	 * Returns the Texture's width.
	 *
	 * @return Texture.resource.width
	 */
	final public int getWidth() {
		return this.getResource().getWidth();
	}

	/**
	 * Returns the Texture's height.
	 *
	 * @return Texture.resource.height
	 */
	final public int getHeight() {
		return this.getResource().getHeight();
	}

	/**
	 * Returns the texture's filename (null if not loaded from filename).
	 *
	 * @return Texture.filename
	 */
	@Contract(pure = true)
	final public @Nullable String getFilename() {
		return this.filename;
	}

	/**
	 * Returns the Texture's fbo.
	 *
	 * @return Texture.resource.fbo
	 */
	private int getFBO() {
		return this.getResource().getFBO();
	}

	/**
	 * Returns the Texture's attachment.
	 *
	 * @return Texture.resource.attachment
	 */
	private int getAttachment() {
		return this.getResource().getAttachment();
	}

	/**
	 * Creates a Texture for OpenGL with the right dimensions, and returns its id, width and height.
	 *
	 * @param width Texture's width
	 * @param height Texture's height
	 * @return new int[3]
	 */
	private static @NotNull int[] create(final int width, final int height) {
		if(width < 1 || height < 1) {
			System.err.println("Error: width and height should be at least 1,");
			System.err.println("width: " + width + " ; height: " + height);
			new Exception().printStackTrace();
			System.exit(1);
		}

		return Texture.generateTexture(width, height, true, new int[width * height]);
	}

	/**
	 * Loads a Texture for OpenGL from a file and returns its id, width and height.
	 *
	 * @param filename File name
	 * @return new int[3]
	 */
	private static @NotNull int[] load(final @NotNull String filename) {
		String fileName = "/media/texture/" + filename + ".png";

		BufferedImage img = support.File.getImage(fileName, false);
		if(img == null) {
			fileName = "/media/texture/" + filename + "_" + Options.LANGUAGE + ".png";
			img = support.File.getImage(fileName, false);
			if(img == null) {
				throw new NullPointerException("Image '" + filename + "' does not exists.");
			}
		}

		final boolean hasAlpha = img.getColorModel().hasAlpha();

		final int width = img.getWidth();
		final int height = img.getHeight();

		final int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);

		return Texture.generateTexture(width, height, hasAlpha, pixels);
	}

	/**
	 * Generates the Texture and returns its id, width and height.
	 *
	 * @param width Texture's width
	 * @param height Texture's height
	 * @param hasAlpha true = Texture contains some opacity
	 * @param pixels Texture's pixels
	 * @return new int[3]
	 */
	private static @NotNull int[] generateTexture(final int width, final int height, final boolean hasAlpha, final int[] pixels) {
		final ByteBuffer buffer = BufferUtil.createFlippedBuffer(width, height, hasAlpha, pixels);

		final int id = glGenTextures();

		// Check if the id is not an id that has to be removed
		for(int i = 0; i < TextureResource.buffersToDelete.size(); i++) {
			if(id == TextureResource.buffersToDelete.get(i)) {
				TextureResource.buffersToDelete.remove(i);
				break;
			}
		}

		glBindTexture(GL_TEXTURE_2D, id);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

		return new int[] {id, width, height};
	}

}