package com.menu.options.tabs.content.slot.checkBox;

import com.GameOptions;
import com.menu.options.tabs.content.TabsContent;
import com.menu.options.tabs.content.slot.TabsContentSlot;
import engine.math.Vector2f;

public class TabsContentSlotCheckBox extends TabsContentSlot {

    /**
     * Creates a new TabsContentSlotCheckBox instance.
     *
     * @param label Label to display
     * @param index Index in the GameOptions
     * @param pos TabsContentSlotCheckBox's position
     * @param contentParent Panel's content
     */
    public TabsContentSlotCheckBox(final String label, final String index, final Vector2f pos, final TabsContent contentParent) {
        super(label, index, new TabsContentSlotCheckBoxInput(GameOptions.getBoolean(index).get()), pos, contentParent);
    }

    @Override
    public void onClick() {
        super.onClick();

        GameOptions.getBoolean(this.getIndex()).set(!GameOptions.getBoolean(this.getIndex()).get());
    }

    @Override
    public TabsContentSlotCheckBoxInput getInput() {
        return (TabsContentSlotCheckBoxInput) super.getInput();
    }

}