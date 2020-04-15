package com.menu.options.tabs.content.slot.slideBar;

import com.GameOptions;
import engine.game.objects.button.slideBar.SlideBar;
import support.SlideValues;

class TabsContentSlotSlideBarObject extends SlideBar {

	/**
	 * Index of the options in the GameOptions.
	 */
	final private String index;

	/**
	 * Creates a new TabsContentSlotSlideBarObject instance.
	 *
	 * @param index Index to the GameOptions
	 * @param values SlideBar's values
	 */
	TabsContentSlotSlideBarObject(final String index, final SlideValues values) {
		super("options " + index, new TabsContentSlotSlideBarDisplay(values.getValue(), values.isPercentage()).init(), TabsContentSlotSlideBarInput.WIDTH, TabsContentSlotSlideBarInput.HEIGHT, values);

		this.index = index;

		this.setDepth(-0.01f);
		this.setBar(new TabsContentSlotBar(this));
	}

	/**
	 * Returns the SlideBar's index the option refers to.
	 *
	 * @return TabsContentSlotSlideBarObject.index
	 */
	private String getIndex() {
		return this.index;
	}

	@Override
	protected void setValue(final int value) {
		super.setValue(value);

		GameOptions.getSlide(index).setValue(value);
	}

}