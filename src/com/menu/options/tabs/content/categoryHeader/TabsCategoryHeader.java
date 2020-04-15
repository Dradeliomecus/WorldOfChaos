package com.menu.options.tabs.content.categoryHeader;

import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.util.Window;

public class TabsCategoryHeader extends GameObject {

    /**
     * ControlsTabsContentCategoryHeader's x position.
     */
    final public static float X_POS = 0.0302f * Window.getRatio();

    /**
     * ControlsTabsContentCategoryHeader's with.
     */
    final public static float WIDTH = 1.27155f * Window.getRatio();

    /**
     * ControlsTabsContentCategoryHeader's height.
     */
    final public static float HEIGHT = 0.09375f;

    /**
     * Creates a new TabsCategoryHeader instance.
     *
     * @param name Category's name
     * @param yPos Y position
     */
    public TabsCategoryHeader(final String name, final float yPos) {
        super("Category " + name, TabsCategoryHeader.WIDTH, TabsCategoryHeader.HEIGHT);

        this.setPosition(new Vector2f(TabsCategoryHeader.X_POS, yPos));
        this.setDepth(-0.01f);

        final TabsCategoryHeaderLabel label = new TabsCategoryHeaderLabel(name).init();
        final float decorationWidth = (this.getWidth() - label.getWidth() - 8.0f/464.0f) / 2.0f;
        final TabsCategoryHeaderDecoration leftDecoration = new TabsCategoryHeaderDecoration(decorationWidth, false);
        final TabsCategoryHeaderDecoration rightDecoration = new TabsCategoryHeaderDecoration(decorationWidth, true);

        this.addChild(label);
        this.addChild(leftDecoration);
        this.addChild(rightDecoration);
    }

}