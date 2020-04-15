package engine.game.objects.tab;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;

public class Tabs extends GameObject {

	/**
	 * Tabs.
	 */
	final private Tab[] tabs;

	/**
	 * Tabs backgrounds (one for each tab).
	 */
	private RenderedComponent[] backgrounds;

	/**
	 * Tabs contents (one for each tab).
	 */
	private GameObject[] contents;

	/**
	 * Tabs buttons (one for each tab).
	 */
	private TabsButton[] buttons;

	/**
	 * Index currently chosen.
	 */
	private int index;

	/**
	 * Creates a new Tabs instance.
	 *
	 * @param name Tabs' name
	 * @param length Number of oldTabs
	 * @param width Tabs' width
	 * @param height Tabs' height
	 */
	public Tabs(final String name, final int length, final float width, final float height) {
		super("Tabs " + name, width, height);

		this.tabs = new Tab[length];
	}

	/**
	 * Returns the numbers of oldTabs.
	 *
	 * @return Tabs.oldTabs.length
	 */
	final public int getLength() {
		return this.getTabs().length;
	}

	/**
	 * Returns the Tabs' oldTabs.
	 *
	 * @return Tabs.oldTabs
	 */
	final protected Tab[] getTabs() {
		return this.tabs;
	}

	/**
	 * Returns a specific Tab.
	 *
	 * @param index Tab's index
	 * @return Tabs.oldTabs[index]
	 */
	final protected Tab getTab(final int index) {
		if(index < 0 || index >= this.getLength()) {
			throw new IllegalArgumentException("Index chosen : " + index + ", number of oldTabs : " + this.getLength());
		}

		return this.getTabs()[index];
	}

	/**
	 * Returns all the backgrounds.
	 *
	 * @return Tabs.backgrounds
	 */
	private RenderedComponent[] getBackgrounds() {
		return this.backgrounds;
	}

	/**
	 * Returns the background of a specific tab.
	 *
	 * @param index Tab's index
	 * @return Tabs.backgrounds[index]
	 */
	private RenderedComponent getBackground(final int index) {
		if(this.getBackgrounds() == null) {
			return null;
		}

		if(index < 0 || index >= this.getLength()) {
			throw new IllegalArgumentException("Index chosen : " + index + ", number of oldTabs : " + this.getLength());
		}

		return this.getBackgrounds()[index];
	}

	/**
	 * Returns all the contents.
	 *
	 * @return Tabs.contents
	 */
	private GameObject[] getContents() {
		return this.contents;
	}

	/**
	 * Returns the content of a specific tab.
	 *
	 * @param index Tab's index
	 * @return Tabs.content[index]
	 */
	private GameObject getContent(final int index) {
		if(this.getContents() == null) {
			return null;
		}

		if(index < 0 || index >= this.getLength()) {
			throw new IllegalArgumentException("Index chosen : " + index + ", number of oldTabs : " + this.getLength());
		}

		return this.getContents()[index];
	}

	/**
	 * Returns all the buttons.
	 *
	 * @return Tabs.buttons
	 */
	private TabsButton[] getButtons() {
		return this.buttons;
	}

	/**
	 * Returns the button of a specific tab.
	 *
	 * @param index Tab's index
	 * @return Tabs.buttons[index]
	 */
	private TabsButton getButton(final int index) {
		if(this.getButtons() == null) {
			return null;
		}

		if(index < 0 || index >= this.getLength()) {
			throw new IllegalArgumentException("Index chosen : " + index + ", number of oldTabs : " + this.getLength());
		}

		return this.getButtons()[index];
	}

	/**
	 * Returns the tab's currently chosen index.
	 *
	 * @return Tabs.index
	 */
	private int getIndex() {
		return this.index;
	}

	/**
	 * Sets the Tabs backgrounds.
	 *
	 * @param backgrounds Backgrounds to set
	 */
	final protected void setBackgrounds(final RenderedComponent[] backgrounds) {
		if(backgrounds.length != this.getLength()) {
			throw new IllegalArgumentException("The number of backgrounds is not equals to the numbers of oldTabs :\n" + this.getLength() + " oldTabs, " + backgrounds.length + " backgrounds.");
		}

		// Clears the old backgrounds if they do exists
		if(this.getBackgrounds() != null) {
			for(final RenderedComponent background : this.getBackgrounds()) {
				this.removeComponent(background);
			}
		}

		this.backgrounds = backgrounds;
		this.addComponent(backgrounds[this.getIndex()]);
	}

	/**
	 * Sets the oldTabs contents.
	 *
	 * @param contents Contents to set
	 */
	final protected void setContents(final GameObject[] contents) {
		if(contents.length != this.getLength()) {
			throw new IllegalArgumentException("The number of contents is not equals to the number of oldTabs:\n" + this.getLength() + " oldTabs, " + contents.length + " contents");
		}

		// Clears the old contents if they do exists.
		if(this.getContents() != null) {
			for(final GameObject object : this.getContents()) {
				this.removeChild(object);
			}
		}

		this.contents = contents;
		this.addChild(contents[this.getIndex()]);
	}

	/**
	 * Sets the Tabs buttons.
	 *
	 * @param buttons Button to set
	 */
	final protected void setButtons(final TabsButton[] buttons) {
		if(buttons.length != this.getLength()) {
			throw new IllegalArgumentException("The number of backgrounds is not equals to the numbers of buttons :\n" + this.getLength() + " oldTabs, " + buttons.length + " backgrounds.");
		}

		// Clears the old buttons if they do exists
		if(this.getButtons() != null) {
			for(final TabsButton button : this.getButtons()) {
				this.removeChild(button);
			}
		}

		this.buttons = buttons;
		buttons[this.getIndex()].setActive(false);

		for(final TabsButton button : buttons) {
			this.addChild(button);
		}
	}

	/**
	 * Sets the tab's currently chosen index.
	 *
	 * @param index Index to set
	 */
	final void setIndex(final int index) {
		if(index < 0 || index >= this.getLength()) {
			throw new IllegalArgumentException("Index chosen : " + index + ", number of oldTabs : " + this.getLength());
		}

		if(this.getBackgrounds() != null) {
			this.removeComponent(this.getBackground(this.getIndex()));
			this.addComponent(this.getBackground(index));
		}

		if(this.getContents() != null) {
			this.removeChild(this.getContent(this.getIndex()));
			this.addChild(this.getContent(index));
		}

		this.getButton(this.getIndex()).setActive(true);
		this.getButton(index).setActive(false);
		this.index = index;
	}

}