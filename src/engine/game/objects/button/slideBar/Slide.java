package engine.game.objects.button.slideBar;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;

public class Slide extends Button {

	/**
	 * Creates a new Slide instance.
	 *
	 * @param name Slide's name
	 * @param style Slide's style
	 */
	public Slide(final String name, final ButtonStyle style) {
		super("Slide " + name, style);
	}

}