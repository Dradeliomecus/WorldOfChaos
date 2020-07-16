package engine.util;

import engine.math.Vector2f;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL30.GL_DRAW_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;

final public class Window {

	/**
	 * Creates the main window for the application.
	 *
	 * @param width Window's width (in px)
	 * @param height Window's height (in px)
	 * @param title Window's title (in px)
	 * @param contextAttribs Context Attributes (for openGl version)
	 */
	public static void create(final int width, final int height, final @NotNull String title, final ContextAttribs contextAttribs) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create(new PixelFormat(), contextAttribs);
		} catch(final LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Returns if the user requested to close to Window.
	 *
	 * @return Window's closure requested
	 */
	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}

	/**
	 * Returns the Window's width.
	 *
	 * @return Window's width (in px)
	 */
	public static int getWidth() {
		return Display.getDisplayMode().getWidth();
	}

	/**
	 * Returns the Window's height.
	 *
	 * @return Window's height (in px)
	 */
	public static int getHeight() {
		return Display.getDisplayMode().getHeight();
	}

	/**
	 * Returns the Window's title.
	 *
	 * @return Window's title
	 */
	public static @NotNull String getTitle() {
		return Display.getTitle();
	}

	/**
	 * Sets the Window's icon.
	 *
	 * @param icon1 First icon's filename to set
	 * @param icon2 Second icon's filename to set
	 */
	public static void setIcon(final @NotNull String icon1, final @NotNull String icon2) {
		final String filename = "/media/icons/";

		Display.setIcon(new ByteBuffer[] {
			BufferUtil.createFlippedBuffer(filename + icon1),
			BufferUtil.createFlippedBuffer(filename + icon2),
		});
	}

	/**
	 * Binds the window as the render target (render to window).
	 */
	public static void bindAsRenderTarget() {
		glBindFramebuffer(GL_DRAW_FRAMEBUFFER, 0);
		glViewport(0, 0, Window.getWidth(), Window.getHeight());
	}

	/**
	 * Returns the Window's ratio (width / height).
	 *
	 * @return new float
	 */
	public static float getRatio() {
		return (float) Window.getWidth() / (float) Window.getHeight();
	}

	/**
	 * Returns the Window's center.
	 *
	 * @return new Vector2f
	 */
	@Contract(" -> new")
	public static @NotNull Vector2f getCenter() {
		return new Vector2f(Window.getWidth() / 2.0f, Window.getHeight() / 2.0f);
	}

	/**
	 * Disposes the Window.
	 */
	public static void dispose() {
		Display.destroy();
	}

	/**
	 * Renders everything on the Window.
	 */
	public static void render() {
		Display.update();
	}

}
