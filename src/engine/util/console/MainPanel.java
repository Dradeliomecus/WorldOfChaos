package engine.util.console;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

class MainPanel extends JPanel {

	/**
	 * Panel's text area.
	 */
	final private JTextArea text;

	/**
	 * Creates a new MainPanel instance.
	 *
	 * @param width Panel's width
	 * @param height Panel's height
	 */
	MainPanel(final int width, final int height) {
		super();

		this.setLayout(new BorderLayout());

		this.setBounds(0, 0, width, height);
		this.setBackground(Color.BLACK);

		this.text = new JTextArea();
		this.initTextArea(width, height);
	}

	/**
	 * Adds some text to the text area.
	 *
	 * @param text Text to add
	 */
	final void print(final String text) {
		this.getTextArea().append(text);
	}

	/**
	 * Updates the panel.
	 */
	final void update() {
		this.getTextArea().setCaretPosition(this.getTextArea().getDocument().getLength());
	}

	/**
	 * Returns the Panel's text area.
	 *
	 * @return MainPanel.text
	 */
	private JTextArea getTextArea() {
		return this.text;
	}

	/**
	 * Initializes the text area.
	 *
	 * @param width Text area width
	 * @param height Text area height
	 */
	private void initTextArea(final int width, final int height) {
		final int BOUNDS = 20;

		this.getTextArea().setBounds(BOUNDS, BOUNDS, width - 2 * BOUNDS, height - 2 * BOUNDS);

		this.getTextArea().setBackground(Color.BLACK);
		this.getTextArea().setForeground(Color.WHITE);
		this.getTextArea().setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		this.getTextArea().setSelectionColor(Color.ORANGE);
		this.getTextArea().setCaretColor(Color.WHITE);

		final JScrollPane scroll = new JScrollPane(this.getTextArea(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scroll, BorderLayout.CENTER);
	}

}