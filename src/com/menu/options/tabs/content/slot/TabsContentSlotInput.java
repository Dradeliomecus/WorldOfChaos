package com.menu.options.tabs.content.slot;

import engine.game.objects.GameObject;

abstract public class TabsContentSlotInput extends GameObject {

    /**
     * Creates a new TabsContentSlotInput instance.
     *
     * @param name TabsContentSlotInput's name
     * @param width TabsContentSlotInput's width
     * @param height TabsContentSlotInput's height
     */
    protected TabsContentSlotInput(final String name, final float width, final float height) {
        super(name, width, height);

        this.setDepth(-0.01f);
    }

    /**
     * Called when the TabsContentSlot is clicked.
     */
    protected void onClick() { }

}