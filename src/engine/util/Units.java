package engine.util;

import engine.math.Vector2f;

final public class Units {

	/**
	 * Returns the pixels length of a Vector2f in OpenGL units.
	 *
	 * @param r Length in OpenGL units
	 * @return new Vector2f
	 */
	public static Vector2f openGLToPixels(final Vector2f r) {
		final Vector2f openGL = new Vector2f(r.getX() / Window.getRatio(), r.getY());

		return new Vector2f(openGL.getX() * Window.getWidth() / 2, openGL.getY() * Window.getHeight() / 2);
	}

	/**
	 * Returns the openGL units length of a Vector2f in pixels.
	 *
	 * @param r Length in pixels
	 * @return new Vector2f
	 */
	public static Vector2f pixelsToOpenGL(final Vector2f r) {
		final Vector2f openGL = new Vector2f(r.getX() * Window.getRatio(), r.getY());

		return new Vector2f(openGL.getX() * 2 / Window.getWidth(), openGL.getY() * 2 / Window.getHeight());
	}

}