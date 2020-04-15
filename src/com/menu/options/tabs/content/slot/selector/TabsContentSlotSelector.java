package com.menu.options.tabs.content.slot.selector;

import com.menu.options.tabs.content.TabsContent;
import com.menu.options.tabs.content.slot.TabsContentSlot;
import engine.math.Vector2f;

public class TabsContentSlotSelector extends TabsContentSlot {

    /**
     * Creates a new TabsContentSlotSelector instance.
     *
     * @param label Label to display
     * @param index Index in the GameOptions
     * @param pos TabsContentSlotSelector's position
     * @param contentParent Panel's content
     */
    public TabsContentSlotSelector(final String label, final String index, final Vector2f pos, final TabsContent contentParent) {
        super(label, index, new TabsContentSlotSelectorInput(index), pos, contentParent);

        this.setActive(false);
    }

    @Override
    public TabsContentSlotSelectorInput getInput() {
        return (TabsContentSlotSelectorInput) super.getInput();
    }

}