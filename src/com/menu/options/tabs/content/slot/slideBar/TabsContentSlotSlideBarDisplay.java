package com.menu.options.tabs.content.slot.slideBar;

import engine.game.objects.button.slideBar.SlideBarDisplay;
import engine.game.objects.text.FontLoader;
import engine.math.Vector2f;
import engine.util.Color;
import engine.util.Window;

class TabsContentSlotSlideBarDisplay extends SlideBarDisplay {

	/**
	 * TabsContentSlotSlideBarDisplay's x position.
	 */
	final public static float X_POS = 0.23275862069f * Window.getRatio();

	/**
	 * TabsContentSlotSlideBarDisplay's y position.
	 */
	final public static float Y_POS = 0.0078125f;

	/**
	 * TabsContentSlotSlideBarDisplay's width.
	 */
	final public static float WIDTH = 0.099137931034483f * Window.getRatio();

	/**
	 * TabsContentSlotSlideBarDisplay's height.
	 */
	final public static float HEIGHT = 0.0625f;

	/**
	 * TabsContentSlotSlideBarDisplay's color.
	 */
	final public static Color COLOR = new Color(179, 179, 179);

	/**
	 * Creates a new TabsContentSlotSlideBarDisplay instance.
	 *
	 * @param startingValue Value to begin with
	 * @param isPercentage Is the display in percentage
	 */
	TabsContentSlotSlideBarDisplay(final int startingValue, final boolean isPercentage) {
		super(startingValue, FontLoader.getFont("tiny"), isPercentage, TabsContentSlotSlideBarDisplay.WIDTH, TabsContentSlotSlideBarDisplay.HEIGHT, new Vector2f(TabsContentSlotSlideBarDisplay.X_POS, TabsContentSlotSlideBarDisplay.Y_POS));

		this.setColor(TabsContentSlotSlideBarDisplay.COLOR);
	}

}