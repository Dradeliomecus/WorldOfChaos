package com.menu.options.tabs.content.slot.selector;

import engine.game.objects.button.selector.SelectorDisplay;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.util.Window;

class TabsContentSlotSelectorDisplay extends SelectorDisplay {

    /**
     * TabsContentSlotSelectorDisplay's x position.
     */
    final public static float X_POS = 0.051724137931f * Window.getRatio();

    /**
     * TabsContentSlotSelectorDisplay's y position.
     */
    final public static float Y_POS = -0.0234375f;

    /**
     * TabsContentSlotSelectorDisplay's width.
     */
    final public static float WIDTH = 0.215517241379f * Window.getRatio();

    /**
     * TabsContentSlotSelectorDisplay's height.
     */
    final public static float HEIGHT = 0.09375f;

    /**
     * Creates a new TabsContentSlotSelectorDisplay instance.
     *
     * @param materials TabsContentSlotSelectorDisplay's materials
     * @param index TabsContentSlotSelectorDisplay's index
     */
    TabsContentSlotSelectorDisplay(final Material[] materials, final int index) {
        super("Menu Selector Display", materials, TabsContentSlotSelectorDisplay.WIDTH, TabsContentSlotSelectorDisplay.HEIGHT, new Vector2f(TabsContentSlotSelectorDisplay.X_POS, TabsContentSlotSelectorDisplay.Y_POS), index);
    }

}