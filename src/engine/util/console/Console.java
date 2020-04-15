package engine.util.console;

import javax.swing.JFrame;
import java.awt.Dimension;

public class Console extends JFrame {

	/**
	 * Console's size.
	 */
	final public static Dimension SIZE = new Dimension(600, 300);

	/**
	 * Console's window's main panel.
	 */
	final private MainPanel mainPanel;

	/**
	 * Creates a new Console instance.
	 *
	 * @param consoleName Console's name
	 */
	public Console(final String consoleName) {
		super(consoleName);

		this.setSize(Console.SIZE);
		this.setPreferredSize(Console.SIZE);
		this.setMinimumSize(Console.SIZE);
		this.setMaximumSize(Console.SIZE);
		this.setLocation(1200, 50);

		this.mainPanel = new MainPanel((int) Console.SIZE.getWidth(), (int) Console.SIZE.getHeight());
		this.getContentPane().add(this.getMainPanel());

		this.setVisible(true);
	}

	/**
	 * Prints some text to the console.
	 *
	 * @param text Text to print
	 */
	final public void print(final String text) {
		this.getMainPanel().print(text);
	}

	/**
	 * Prints some double to the console.
	 *
	 * @param number Double to print
	 */
	final public void print(final double number) {
		this.print(number + "");
	}

	/**
	 * Prints some text to the console and returns to the next line.
	 *
	 * @param text Text to print
	 */
	final public void println(final String text) {
		this.print(text + "\n");
	}

	/**
	 * Print some double to the console and returns to the next line.
	 *
	 * @param number Double to print
	 */
	final public void println(final double number) {
		this.println(number + "");
	}

	/**
	 * Updates the console.
	 */
	final public void update() {
		this.getMainPanel().update();
	}

	/**
	 * Returns the Console's window's main panel.
	 *
	 * @return Console.mainPanel
	 */
	private MainPanel getMainPanel() {
		return this.mainPanel;
	}

}