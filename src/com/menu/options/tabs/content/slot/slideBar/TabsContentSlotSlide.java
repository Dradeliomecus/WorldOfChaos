package com.menu.options.tabs.content.slot.slideBar;

import engine.game.objects.button.ButtonStyle;
import engine.game.objects.button.slideBar.Slide;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Color;
import engine.util.Window;

class TabsContentSlotSlide extends Slide {

    /**
     * TabsContentSlotSlide's width.
     */
    final public static float WIDTH = 0.0086206896552f * Window.getRatio();

    /**
     * TabsContentSlotSlide's height.
     */
    final public static float HEIGHT = 0.0546875f;

    /**
     * TabsContentSlotSlide's button style.
     */
    final public static ButtonStyle BUTTON_STYLE = new ButtonStyle(
        new Material(new Texture("blank1x1"), new Color(59, 59, 59)),
        new Material(new Texture("blank1x1"), new Color(68, 68, 68)),
        new Material(new Texture("blank1x1"), new Color(75, 75, 75)),
        null,
        TabsContentSlotSlide.WIDTH, TabsContentSlotSlide.HEIGHT
    );

    /**
     * Creates a new TabsContentSlotSlide instance.
     */
    TabsContentSlotSlide() {
        super("Tabs Content slot", TabsContentSlotSlide.BUTTON_STYLE);

        this.setDepth(-0.01f);
        this.setOverrideGlobalActivation((byte) 1);
    }

}