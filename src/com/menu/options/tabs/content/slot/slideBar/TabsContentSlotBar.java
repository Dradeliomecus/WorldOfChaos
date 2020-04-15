package com.menu.options.tabs.content.slot.slideBar;

import engine.game.objects.button.ButtonStyle;
import engine.game.objects.button.slideBar.Bar;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class TabsContentSlotBar extends Bar {

    /**
     * TabsContentSlotBar's width.
     */
    final public static float WIDTH = 0.2241379310345f * Window.getRatio();

    /**
     * TabsContentSlotBar's height.
     */
    final public static float HEIGHT = 0.0546875f;

    /**
     * TabsContentSlotBar's button style.
     */
    final public static ButtonStyle BUTTON_STYLE = new ButtonStyle(
        new Material(new Texture("/menu/optionsPanel/tabs/bar")),
        new Material(new Texture("/menu/optionsPanel/tabs/bar-over")),
        new Material(new Texture("/menu/optionsPanel/tabs/bar-onclick")),
        null,
        TabsContentSlotBar.WIDTH, TabsContentSlotBar.HEIGHT
    );

    /**
     * Creates a new TabsContentSlotBar instance.
     *
     * @param parent SlideBar parent
     */
    TabsContentSlotBar(final TabsContentSlotSlideBarObject parent) {
        super("tabs options bar", TabsContentSlotBar.BUTTON_STYLE, parent);

        this.setDepth(-0.01f);
        this.setOverrideGlobalActivation((byte) 1);
        this.setSlide(new TabsContentSlotSlide());
    }

}