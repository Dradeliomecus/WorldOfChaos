package com.menu.options.tabs.content.slot.selector;

import com.GameOptions;
import com.menu.options.tabs.content.slot.TabsContentSlotInput;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.util.Window;

public class TabsContentSlotSelectorInput extends TabsContentSlotInput {

    /**
     * TabsContentSlotSelectorInput's x position.
     */
    final public static float X_POS = 0.2413793103448f * Window.getRatio();

    /**
     * TabsContentSlotSelectorInput's y position.
     */
    final public static float Y_POS = 0.0234375f;

    /**
     * TabsContentSlotSelectorInput's width.
     */
    final public static float WIDTH = 0.31896551724f * Window.getRatio();

    /**
     * TabsContentSlotSelectorInput's height.
     */
    final public static float HEIGHT = 0.0703125f;

    /**
     * Creates a new TabsContentSlotSelectorInput instance.
     *
     * @param index Index of the selector in the GameOptions
     */
    TabsContentSlotSelectorInput(final String index) {
        super("Options Tabs selector input", TabsContentSlotSelectorInput.WIDTH, TabsContentSlotSelectorInput.HEIGHT);

        this.setPosition(new Vector2f(TabsContentSlotSelectorInput.X_POS, TabsContentSlotSelectorInput.Y_POS));

        final support.Select select = GameOptions.getSelect(index);
        final Material[] materials = new Material[select.getLength()];

        for(int i = 0; i < select.getLength(); i++) {
            final SelectorText text = new SelectorText(select.getOption(i)).init();
            materials[i] = text.getTextMaterial();
        }

        final TabsContentSlotSelectorObject selector = new TabsContentSlotSelectorObject(materials, select.getIndex(), index);

        this.addChild(selector);
    }

}