package engine.util;

import engine.game.components.GameComponent;
import engine.rendering.shader.Shader;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public interface GameInterface {

	/**
	 * Called after the constructor to initializes the class after having been built.
	 *
	 * @return this
	 */
	abstract public GameInterface init();

	/**
	 * Called every frame to render the class on the screen.
	 *
	 * @param shader Shader to render with
	 * @param renderLater List of components to render after everything has been render (e.g. because of 0 < opacity < 1).
	 */
	abstract public void render(final Shader shader, final @Nullable ArrayList<GameComponent> renderLater);

	/**
	 * Called every frame to update the class.
	 *
	 * @param delta Time between 2 frames
	 */
	abstract public void update(final double delta);

	/**
	 * Called every frame before update().
	 */
	abstract public void input();

}