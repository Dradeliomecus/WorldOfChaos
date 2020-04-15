package engine.game;

import engine.CoreEngine;
import engine.game.components.GameComponent;
import engine.game.objects.GameObject;
import engine.rendering.RenderingEngine;
import engine.rendering.shader.Shader;
import engine.util.GameInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

abstract public class CoreGame implements GameInterface {

	/**
	 * Game's root object.
	 */
	final private RootObject root;

	/**
	 * Pointer to the rendering engine.
	 */
	private RenderingEngine renderingEngine;

	/**
	 * Creates a new CoreGame instance.
	 */
	public CoreGame() {
		this.root = new RootObject().init();
	}

	/**
	 * Initializes the CoreGame.
	 *
	 * @param coreEngine Core Engine to set
	 * @return self
	 */
	final public CoreGame init(final CoreEngine coreEngine) {
		this.getRootObject().addToEngine(coreEngine);

		return this;
	}

	/**
	 * Adds a child to the root object.
	 *
	 * @param child GameObject to add
	 */
	final protected void addToRootObject(final GameObject child) {
		this.getRootObject().addChildToRootObject(child);
	}

	/**
	 * Removes a child from the root object.
	 *
	 * @param child GameObject to remove
	 */
	final protected void removeFromRootObject(final GameObject child) {
		this.getRootObject().removeChildFromRootObject(child);
	}

	/**
	 * Calls the rendering engine to render all objects and components.
	 *
	 * @param renderingEngine Rendering engine to use to render
	 */
	public void render(final @NotNull RenderingEngine renderingEngine) {
		renderingEngine.render(this.getRootObject());
	}

	@Override
	final public void render(final Shader shader, final @Nullable ArrayList<GameComponent> renderLater) {
		System.err.println("Error: CoreGame.render should never be called.");
		new Exception().printStackTrace();
		System.exit(1);
	}

	@Override
	public void update(final double delta) {
		this.getRootObject().update(delta);
	}

	@Override
	public void input() {
		this.getRootObject().input();
	}

	/**
	 * Returns the RootObject.
	 *
	 * @return CoreGame.root
	 */
	private RootObject getRootObject() {
		return this.root;
	}

	/**
	 * Returns the rendering engine.
	 *
	 * @return CoreGame.renderingEngine
	 */
	final public RenderingEngine getRenderingEngine() {
		return this.renderingEngine;
	}

	/**
	 * Sets the rendering engine.
	 *
	 * @param renderingEngine Rendering engine to set
	 */
	final public void setRenderingEngine(final RenderingEngine renderingEngine) {
		this.renderingEngine = renderingEngine;
	}

}
