package com.menu.options.tabs.content.scrollbar;

import engine.game.objects.scrollbar.Scroll;
import engine.math.Vector2f;
import engine.util.Window;

public class TabsScroll extends Scroll {

    /**
     * TabsScroll's x position.
     */
    final public static float X_POS = 0.004310f * Window.getRatio();

    /**
     * TabsScroll's width.
     */
    final public static float WIDTH = 0.025862f * Window.getRatio();

    /**
     * Scrollbar's height.
     */
    final public static float TOTAL_HEIGHT = 0.875f;

    /**
     * Creates a new TabsScroll instance.
     *
     * @param name TabsScroll's name
     * @param heightRatio Content's height ratio
     * @param deltaHeight Delta height
     */
    public TabsScroll(final String name, final float heightRatio, final float deltaHeight) {
        super("Tabs' " + name + "'s scroll", TabsScroll.WIDTH, TabsScroll.TOTAL_HEIGHT, heightRatio, deltaHeight, new TabsScrollTopFragment(TabsScroll.TOTAL_HEIGHT * heightRatio), new TabsScrollMiddleFragment(TabsScroll.TOTAL_HEIGHT * heightRatio), new TabsScrollBottomFragment());

        this.setPosition(new Vector2f(TabsScroll.X_POS, 0));
    }

}