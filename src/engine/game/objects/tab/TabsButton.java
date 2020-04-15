package engine.game.objects.tab;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;

public class TabsButton extends Button {

	/**
	 * TabsButton's index.
	 */
	final private int index;

	/**
	 * Pointer to the Tabs parent.
	 */
	final private Tabs tabsParent;

	/**
	 * Creates a new TabsButton instance.
	 *
	 * @param parent Tabs parent to set
	 * @param index Button's index
	 * @param buttonStyle Button's style to set
	 */
	public TabsButton(final Tabs parent, final int index, final ButtonStyle buttonStyle) {
		super(parent.getName() + " button n°" + index, buttonStyle);

		this.index = index;
		this.tabsParent = parent;

		this.setDepth(-0.05f);
	}

	@Override
	public void onClick() {
		super.onClick();

		this.getTabsParent().setIndex(this.getIndex());
	}

	/**
	 * Returns the TabsButton's index.
	 *
	 * @return TabsButton.index
	 */
	private int getIndex() {
		return this.index;
	}

	/**
	 * Returns the Button's Tabs parent.
	 *
	 * @return TabsButton.tabsParent
	 */
	private Tabs getTabsParent() {
		return this.tabsParent;
	}

}