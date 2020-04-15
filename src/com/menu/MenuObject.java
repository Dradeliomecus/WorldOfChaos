package com.menu;

import com.Game;
import com.menu.buttons.ContinueButton;
import com.menu.buttons.LoadButton;
import com.menu.buttons.NewGameButton;
import com.menu.buttons.OptionsButton;
import com.menu.buttons.QuitButton;
import com.menu.options.OptionsPanel;
import engine.audio.AudioObject;
import engine.game.components.Camera;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.game.objects.button.Button;
import engine.math.Vector2f;
import engine.rendering.shader.Shader;
import engine.rendering.texture.Animation;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

final public class MenuObject extends GameObject {

	/**
	 * MenuObject's depth.
	 */
	final public static float DEPTH = 0.5f;

	/**
	 * Reference to the parent's game class.
	 */
	final private @NotNull Game game;

	/**
	 * Option's panel.
	 */
	final private @NotNull OptionsPanel optionsPanel;

	/**
	 * Do we display the option's panel.
	 */
	private boolean optionsPanelOn;

	/**
	 * Create a new MenuObject instance.
	 *
	 * @param game Parent's game
	 */
	public MenuObject(final @NotNull Game game) {
		super("Menu Object", 2 * Window.getRatio(), 2);

		this.game = game;
		this.optionsPanel = new OptionsPanel(this);
		this.optionsPanelOn = false;
		this.setPosition(new Vector2f(-1 * Window.getRatio(), -1));
		this.setDepth(MenuObject.DEPTH);

		final Material animationMaterial = new Material(new Animation(new Texture[] {
			new Texture("/menu/background-1"),
			new Texture("/menu/background-2"),
			new Texture("/menu/background-3"),
			new Texture("/menu/background-4"),
			new Texture("/menu/background-5"),
			new Texture("/menu/background-6"),
			new Texture("/menu/background-7"),
			new Texture("/menu/background-8"),
			new Texture("/menu/background-9"),
			new Texture("/menu/background-10"),
			new Texture("/menu/background-11"),
			new Texture("/menu/background-12"),
			new Texture("/menu/background-13"),
			new Texture("/menu/background-14"),
			new Texture("/menu/background-15"),
			new Texture("/menu/background-16"),
			new Texture("/menu/background-17"),
			new Texture("/menu/background-18"),
			new Texture("/menu/background-19"),
			new Texture("/menu/background-20"),
			new Texture("/menu/background-21"),
			new Texture("/menu/background-22"),
			new Texture("/menu/background-23"),
			new Texture("/menu/background-24")
		}, 4f));
		final RenderedComponent renderedComponent = new RenderedComponent(animationMaterial, 2 * Window.getRatio(), 2);
		this.addComponent(renderedComponent);

		final NewGameButton newGameButton = new NewGameButton(this);
		this.addChild(newGameButton);
		newGameButton.init();

		final ContinueButton continueButton = new ContinueButton(this);
		this.addChild(continueButton);
		continueButton.init();

		final LoadButton loadButton = new LoadButton(this);
		this.addChild(loadButton);
		loadButton.init();

		final OptionsButton optionsButton = new OptionsButton(this);
		this.addChild(optionsButton);
		optionsButton.init();

		final QuitButton quitButton = new QuitButton();
		this.addChild(quitButton);
		quitButton.init();

		// Menu background music.
		final AudioObject bkgSound = new AudioObject("/Cry_for_Eternity.wav");
		bkgSound.setVolume(0.01f);
		bkgSound.setPitch(0.07f);
		bkgSound.loops(true);
		bkgSound.update(new Vector2f(0, 0), new Vector2f(0, 0));
		this.addAudioObject(bkgSound);

		//bkgSound.play();
	}

	@Override
	final public @NotNull MenuObject init() {
		final Camera camera = new Camera();

		final MenuCameraObject cameraObject = new MenuCameraObject(camera);
		camera.init();
		this.addChild(cameraObject);
		cameraObject.init();

		this.getGame().getRenderingEngine().setMainCamera(camera);

		return this;
	}

	@Override
	final public void update(final double delta) {
		super.update(delta);

		if(this.optionsPanelOn && !this.getChildren().contains(this.getOptionsPanel())) {
			this.addChild(this.getOptionsPanel());
		}

		if(!this.optionsPanelOn && this.getChildren().contains(this.getOptionsPanel())) {
			this.removeChild(this.getOptionsPanel());
		}
	}

	/**
	 * Adds an options panel.
	 */
	final public void addOptionsPanel() {
		this.optionsPanelOn = true;
		Button.GLOBAL_ACTIVATION = (byte) 1;
	}

	/**
	 * Removes the options panel.
	 */
	final public void removeOptionsPanel() {
		this.optionsPanelOn = false;
		Button.GLOBAL_ACTIVATION = (byte) 0;
	}

	/**
	 * Returns a reference to the parent's game.
	 *
	 * @return MenuObject.game
	 */
	@Contract(pure = true)
	final public @NotNull Game getGame() {
		return this.game;
	}

	/**
	 * Returns the MenuObject's OptionsPanel.
	 *
	 * @return MenuObject.optionsPanel
	 */
	@Contract(pure = true)
	private @NotNull OptionsPanel getOptionsPanel() {
		return this.optionsPanel;
	}

	/**
	 * Called when the menu should no longer exist (since destructor is weird in Java).
	 */
	final public void destroy() {
		for(final AudioObject audioObject : this.getAudioObjects()) {
			audioObject.stop();
		}
	}

}