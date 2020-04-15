package com.menu.options.tabs.content.slot.selector;

import engine.game.objects.text.FontLoader;
import engine.game.objects.text.Text;
import engine.util.Color;

class SelectorText extends Text {

    /**
     * SelectorText's color.
     */
    final public static Color COLOR = new Color(179, 179, 179);

    /**
     * Creates a new SelectorText instance.
     *
     * @param text Text to display
     */
    SelectorText(final String text) {
        super(text, FontLoader.getFont("tiny"), TabsContentSlotSelectorDisplay.WIDTH, TabsContentSlotSelectorDisplay.HEIGHT);

        this.setColor(SelectorText.COLOR);
        this.setTextDecoration(Text.ALIGN_CENTER);
    }

    @Override
    public SelectorText init() {
        this.print();

        return this;
    }

}