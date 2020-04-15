package com.menu.options.tabs.content.slot.selector;

import com.GameOptions;
import engine.game.objects.button.ButtonStyle;
import engine.game.objects.button.selector.LeftSelector;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class TabsContentSlotLeftSelector extends LeftSelector {

    /**
     * TabsContentSlotLeftSelector's width.
     */
    final public static float WIDTH = 0.04310344826f * Window.getRatio();

    /**
     * TabsContentSlotLeftSelector's height.
     */
    final public static float HEIGHT = 0.0703125f;

    /**
     * TabsContentSlotLeftSelector's button style.
     */
    final public static ButtonStyle BUTTON_STYLE = new ButtonStyle(
        new Material(new Texture("/menu/optionsPanel/tabs/selector")),
        new Material(new Texture("/menu/optionsPanel/tabs/selector-over")),
        new Material(new Texture("/menu/optionsPanel/tabs/selector-onclick")),
        new Material(new Texture("/menu/optionsPanel/tabs/selector-off")),
        TabsContentSlotLeftSelector.WIDTH, TabsContentSlotLeftSelector.HEIGHT
    );

    /**
     * Creates a new TabsContentSlotLeftSelector instance.
     *
     * @param selector Selector parent
     */
    TabsContentSlotLeftSelector(final TabsContentSlotSelectorObject selector) {
        super("Menu Left Selector", TabsContentSlotLeftSelector.BUTTON_STYLE, selector);

        this.setDepth(-0.01f);

        this.setOverrideGlobalActivation((byte) 1);
    }

    @Override
    protected TabsContentSlotSelectorObject getParent() {
        return (TabsContentSlotSelectorObject) super.getParent();
    }

    @Override
    public void onClick() {
        super.onClick();

        GameOptions.getSelect(this.getParent().getOptionsIndex()).setIndex(this.getParent().getIndex());
    }

}