package com.menu.options.tabs.content.slot.selector;

import engine.game.objects.button.selector.Selector;
import engine.rendering.texture.Material;

class TabsContentSlotSelectorObject extends Selector {

    /**
     * Index to the GameOptions.
     */
    final private String optionsIndex;

    /**
     * Creates a new TabsContentSlotSelectorObject instance.
     *
     * @param materials Materials to set
     * @param index Index to use
     * @param optionsIndex Index to the GameOptions
     */
    TabsContentSlotSelectorObject(final Material[] materials, final int index, final String optionsIndex) {
        super("Menu Selector", new TabsContentSlotSelectorDisplay(materials, index), TabsContentSlotSelectorInput.WIDTH, TabsContentSlotSelectorInput.HEIGHT, index);

        this.optionsIndex = optionsIndex;

        final TabsContentSlotLeftSelector leftSelector = new TabsContentSlotLeftSelector(this);
        this.setLeftSelector(leftSelector);

        final TabsContentSlotRightSelector rightSelector = new TabsContentSlotRightSelector(this);
        this.setRightSelector(rightSelector);
    }

    /**
     * Returns the index that leads to the GameOptions.
     *
     * @return TabsContentSlotSelectorObject.optionsIndex
     */
    final String getOptionsIndex() {
        return this.optionsIndex;
    }

}