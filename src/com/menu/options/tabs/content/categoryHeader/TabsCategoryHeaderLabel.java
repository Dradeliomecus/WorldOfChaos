package com.menu.options.tabs.content.categoryHeader;

import engine.game.objects.text.FontLoader;
import engine.game.objects.text.Text;
import engine.math.Vector2f;
import engine.util.Color;
import engine.util.Units;

class TabsCategoryHeaderLabel extends Text {

    /**
     * TabsCategoryHeaderLabel's height.
     */
    final public static float HEIGHT = 0.09375f;

    /**
     * TabsCategoryHeaderLabel's color.
     */
    final public static Color COLOR = new Color(133, 133, 133);

    /**
     * Creates a new TabsCategoryHeaderLabel instance.
     *
     * @param text Text to display
     */
    TabsCategoryHeaderLabel(final String text) {
        super(text, FontLoader.getFont("tiny"), TabsCategoryHeaderLabel.getTextWidth(text), TabsCategoryHeaderLabel.HEIGHT);

        this.setColor(TabsCategoryHeaderLabel.COLOR);

        this.setPosition(new Vector2f((TabsCategoryHeader.WIDTH - this.getWidth()) / 2.0f, 0.0f));
        this.setDepth(-0.01f);

        // TODO: This text needs to be underlined
    }

    @Override
    public TabsCategoryHeaderLabel init() {
        this.print();

        return this;
    }

    /**
     * Returns the width of the text label.
     *
     * @param text Text that will be displayed
     * @return new float
     */
    private static float getTextWidth(final String text) {
        return Units.pixelsToOpenGL(new Vector2f(FontLoader.getFont("tiny").getTextPixelWidth(text), 0)).getX() * 3;
    }

}