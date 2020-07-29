package engine;

import com.MainComponent;
import com.Options;
import engine.audio.AudioEngine;
import engine.game.CoreGame;
import engine.physic.PhysicsEngine;
import engine.rendering.RenderingEngine;
import engine.rendering.texture.TextureResource;
import engine.util.Input;
import engine.util.Time;
import engine.util.Window;
import engine.util.profiling.Profiler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL30.glDeleteFramebuffers;

final public class CoreEngine {

	/**
	 * Is the Engine running right now.
	 */
	private boolean isRunning;

	/**
	 * Application's game.
	 */
	final private @NotNull CoreGame game;

	/**
	 * Application's rendering engine.
	 */
	final private @NotNull RenderingEngine renderingEngine;

	/**
	 * Application's audio engine.
	 */
	final private @NotNull AudioEngine audioEngine;

	/**
	 * Creates a new CoreEngine instance.
	 *
	 * @param game Game used for the application.
	 */
	public CoreEngine(final @NotNull CoreGame game) {
		this.isRunning = false;

		this.createWindow();

		this.renderingEngine = new RenderingEngine();
		game.setRenderingEngine(this.renderingEngine);
		this.audioEngine = new AudioEngine(); // TODO: Initialize this later with the loading screen
		Input.init(); // TODO: Initialize this later with the loading screen

		this.game = game.init(this);

		if(Options.DEBUG) System.out.println("CoreEngine has been initialized.");
	}

	/**
	 * Creates a window, initializes the graphics and then, runs the engine.
	 */
	final public void start() {
		if(this.isRunning) {
			System.err.println("Couldn't start the CoreEngine: Engine already running.");
			new Exception().printStackTrace();
			return;
		}

		if(Options.DEBUG) System.out.println("CoreEngine is starting.");

		this.run();
	}

	/**
	 * Runs the engine.
	 */
	private void run() {
		this.isRunning = true;

		int frames = 0;
		long frameCounter = 0L;

		this.game.init();

		long lastFrameTime = Time.getNanoTime(); // When did the last frame start
		double unprocessedTime = 0.0; // How many time do I still need to update the game

		if(Options.DEBUG) System.out.println("CoreEngine is running. It took " + (Time.getNanoTime() - MainComponent.APPLICATION_START) * Time.NANO_TO_MILLI + "ms to get here.");
		if(Options.DEBUG) System.out.println("OpenGL version: " + RenderingEngine.getOpenGLVersion());

		while(this.isRunning) {
			final double frameTime = 1.0 / Options.MAX_FRAMES_PER_SECOND.get(); // How long should a frame take
			boolean render = false;

			final long startTime = Time.getNanoTime(); // When did this frame start
			final long passedTime = startTime - lastFrameTime; // Time the last frame took
			lastFrameTime = startTime;

			unprocessedTime += ((double) passedTime / Time.SECOND_TO_NANO);
			frameCounter += passedTime;

			while(unprocessedTime > frameTime) {
				render = true;

				unprocessedTime -= frameTime;

				if(Window.isCloseRequested()) this.stop();

				Profiler.startProfileTimer("Input");
				this.game.input();
				Input.update();
				Profiler.stopProfileTimer("Input");

				Profiler.startProfileTimer("Update");
				Profiler.update();
				Profiler.startProfileTimer("Update-Physics");
				PhysicsEngine.update(frameTime);
				Profiler.stopProfileTimer("Update-Physics");
				this.game.update(frameTime);
				Profiler.stopProfileTimer("Update");
				this.getAudioEngine().update();

				CoreEngine.cleanMemory();

				if(frameCounter >= Time.SECOND_TO_NANO) {
					if(Profiler.isOn()) {
						final double totalTime = (1000.0 * frameCounter * Time.NANO_TO_SECOND) / (double) frames;

						Profiler.displayAndResetTime(totalTime, frames);
					}

					if(Options.DEBUG) System.out.println(frames + " frames/sec.");
					frames = 0;
					frameCounter = 0L;
				}
			}

			if(render) {
				this.render();

				frames++;
			} else {
				try {
					Profiler.startProfileTimer("Sleep");
					Thread.sleep(1);
					Profiler.stopProfileTimer("Sleep");
				} catch(final InterruptedException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}

		this.cleanUp();

		if(Options.DEBUG) System.out.println("Application exiting.");

		System.exit(0);
	}

	/**
	 * Clears the memory from all things not used anymore.
	 */
	private static void cleanMemory() {
		final ArrayList<Integer> buffersToDelete = new ArrayList<>(TextureResource.buffersToDelete);
		TextureResource.buffersToDelete.clear();

		final ArrayList<Integer> fboToDelete = new ArrayList<>(TextureResource.fboToDelete);
		TextureResource.fboToDelete.clear();

		for(final int id : buffersToDelete) {
			try {
				glDeleteTextures(id);
			} catch(final Exception e) {
				System.out.println("Error when deleting id n°" + id);
				e.printStackTrace();
			}
		}

		for(final int fbo : fboToDelete) {
			try {
				glDeleteFramebuffers(fbo);
			} catch(final Exception e) {
				System.out.println("Error when deleting fbo n°" + fbo);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cleans all components
	 * This method should be called before exiting the application.
	 */
	private void cleanUp() {
		Window.dispose();
		Input.destroy();
		this.getAudioEngine().cleanUp();

		if(Options.DEBUG) System.out.println("\nCoreEngine has been cleanedUp.");
	}

	/**
	 * Stops the engine and the application cleanly.
	 */
	final public void stop() {
		if(!this.isRunning) {
			System.err.println("Couldn't stops the CoreEngine: Engine is not running.");
			new Exception().printStackTrace();
			return;
		}

		this.isRunning = false;

		if(Options.DEBUG) System.out.println("\nStopping the CoreEngine has been requested.");
	}

	/**
	 * Renders a frame.
	 */
	private void render() {
		Profiler.startProfileTimer("Render");
		this.game.render(this.renderingEngine);
		Profiler.stopProfileTimer("Render");

		Profiler.startProfileTimer("WindowSynch");
		Window.render();
		Profiler.stopProfileTimer("WindowSynch");
	}

	/**
	 * Returns the Rendering Engine.
	 *
	 * @return CoreEngine.renderingEngine
	 */
	@Contract(pure = true)
	final public @NotNull RenderingEngine getRenderingEngine() {
		return this.renderingEngine;
	}

	/**
	 * Returns the Audio Engine.
	 *
	 * @return CoreEngine.audioEngine
	 */
	@Contract(pure = true)
	final public @NotNull AudioEngine getAudioEngine() {
		return this.audioEngine;
	}

	/**
	 * Creates the main window for the application and initializes the graphics.
	 */
	private void createWindow() {
		Window.create(Options.WINDOW_WIDTH, Options.WINDOW_HEIGHT, Options.WINDOW_TITLE);
	}

}