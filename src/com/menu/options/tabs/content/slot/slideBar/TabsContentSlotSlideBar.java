package com.menu.options.tabs.content.slot.slideBar;

import com.menu.options.tabs.content.TabsContent;
import com.menu.options.tabs.content.slot.TabsContentSlot;
import engine.math.Vector2f;

public class TabsContentSlotSlideBar extends TabsContentSlot {

    /**
     * Creates a new TabsContentSlotSlideBar instance.
     *
     * @param label Label to display
     * @param index Index in the GameOptions
     * @param pos TabsContentSlotSlideBar's position
     * @param contentParent Panel's content
     */
    public TabsContentSlotSlideBar(final String label, final String index, final Vector2f pos, final TabsContent contentParent) {
        super(label, index, new TabsContentSlotSlideBarInput(index), pos, contentParent);

        this.setActive(false);
    }

    @Override
    public TabsContentSlotSlideBarInput getInput() {
        return (TabsContentSlotSlideBarInput) super.getInput();
    }

}