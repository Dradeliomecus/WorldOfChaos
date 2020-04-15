package com.menu.options.tabs.content.slot;

import engine.game.objects.text.FontLoader;
import engine.game.objects.text.Text;
import engine.math.Vector2f;
import engine.util.Color;
import engine.util.Window;

class TabsContentSlotLabel extends Text {

    /**
     * TabsContentSlotLabel's width.
     */
    final public static float WIDTH = 0.36206897f * Window.getRatio();

    /**
     * TabsContentSlotLabel's height.
     */
    final public static float HEIGHT = 0.09375f;

    /**
     * TabsContentSlotLabel's color.
     */
    final public static Color COLOR = new Color(179, 179, 179);

    /**
     * Creates a new TabsContentSlotLabel instance.
     *
     * @param text Text to display
     */
    TabsContentSlotLabel(final String text) {
        super(text, FontLoader.getFont("tiny"), TabsContentSlotLabel.WIDTH, TabsContentSlotLabel.HEIGHT);

        this.setColor(TabsContentSlotLabel.COLOR);

        this.setPosition(new Vector2f(0.0301724f * Window.getRatio(), 0));
        this.setDepth(-0.01f);
    }

    @Override
    public TabsContentSlotLabel init() {
        this.print();

        return this;
    }

}