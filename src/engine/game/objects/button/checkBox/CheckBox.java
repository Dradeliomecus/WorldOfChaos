package engine.game.objects.button.checkBox;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import org.jetbrains.annotations.NotNull;

public class CheckBox extends Button {

	/**
	 * Is the checkbox enabled.
	 */
	final private @NotNull Check check;

	/**
	 * Creates a new CheckBox instance.
	 *
	 * @param name CheckBox's name
	 * @param style CheckBox's style
	 * @param check CheckBox little cutie check.
	 */
	public CheckBox(final @NotNull String name, final @NotNull ButtonStyle style, final @NotNull Check check) {
		super(name, style);

		this.check = check;
		this.addChild(check);
	}

	@Override
	public void onClick() {
		super.onClick();

		if(this.getCheck().getStatus() == 1) {
			this.getCheck().setStatus((byte) 0);
		} else if(this.getCheck().getStatus() == 0) {
			this.getCheck().setStatus((byte) 1);
		}
	}

	/**
	 * Returns the CheckBox's check.
	 *
	 * @return CheckBox.check
	 */
	private @NotNull Check getCheck() {
		return this.check;
	}

}