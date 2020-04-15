package com.menu.options.tabs.content.slot.slideBar;

import com.GameOptions;
import com.menu.options.tabs.content.slot.TabsContentSlotInput;
import engine.math.Vector2f;
import engine.util.Window;
import support.SlideValues;

public class TabsContentSlotSlideBarInput extends TabsContentSlotInput {

    /**
     * TabsContentSlotSlideBarInput's x position.
     */
    final public static float X_POS = 0.2413793103448f * Window.getRatio();

    /**
     * TabsContentSlotSlideBarInput's y position.
     */
    final public static float Y_POS = 0.015625f;

    /**
     * TabsContentSlotSlideBarInput's width.
     */
    final public static float WIDTH = 0.31896551724f * Window.getRatio();

    /**
     * TabsContentSlotSlideBarInput's height.
     */
    final public static float HEIGHT = 0.0625f;

    /**
     * Creates a new TabsContentSlotSelectorInput instance.
     *
     * @param index Index of the selector in the GameOptions
     */
    TabsContentSlotSlideBarInput(final String index) {
        super("Options Tabs slide bar input", TabsContentSlotSlideBarInput.WIDTH, TabsContentSlotSlideBarInput.HEIGHT);

        this.setPosition(new Vector2f(TabsContentSlotSlideBarInput.X_POS, TabsContentSlotSlideBarInput.Y_POS));

        final SlideValues slideValues = GameOptions.getSlide(index);

        final TabsContentSlotSlideBarObject slideBar = new TabsContentSlotSlideBarObject(index, slideValues);
        this.addChild(slideBar);
    }

}