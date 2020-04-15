package com.menu.options.tabs.content.slot.checkBox;

import com.menu.options.tabs.content.slot.TabsContentSlotInput;
import engine.game.components.RenderedComponent;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class TabsContentSlotCheckBoxInput extends TabsContentSlotInput {

    /**
     * TabsContentSlotCheckBoxInput's x position.
     */
    final public static float X_POS = 0.426724137931f * Window.getRatio();

    /**
     * TabsContentSlotCheckBoxInput's y position.
     */
    final public static float Y_POS = 0.0234375f;

    /**
     * TabsContentSlotCheckBoxInput's width.
     */
    final public static float WIDTH = 0.038793103f * Window.getRatio();

    /**
     * TabsContentSlotCheckBoxInput's height.
     */
    final public static float HEIGHT = 0.0703125f;

    /**
     * TabsContentSlotCheckBoxInput's material when check box is OK.
     */
    final public static Material MATERIAL_YES = new Material(new Texture("/menu/optionsPanel/tabs/checkBoxYes"));

    /**
     * TabsContentSlotCheckBoxInput's material when check box is NO.
     */
    final public static Material MATERIAL_NO = new Material(new Texture("/menu/optionsPanel/tabs/checkBoxNo"));

    /**
     * TabsContentSlotCheckBoxInput's current rendered component.
     */
    final private RenderedComponent renderedComponent;

    /**
     * Creates a new TabsContentSlotCheckBoxInput instance.
     *
     * @param defaultStatus CheckBox default status
     */
    public TabsContentSlotCheckBoxInput(final boolean defaultStatus) {
        super("Options Tabs check box input", TabsContentSlotCheckBoxInput.WIDTH, TabsContentSlotCheckBoxInput.HEIGHT);

        this.setPosition(new Vector2f(TabsContentSlotCheckBoxInput.X_POS, TabsContentSlotCheckBoxInput.Y_POS));
        this.setDepth(-0.01f);

        this.renderedComponent = new RenderedComponent(defaultStatus ? TabsContentSlotCheckBoxInput.MATERIAL_YES : TabsContentSlotCheckBoxInput.MATERIAL_NO, TabsContentSlotCheckBoxInput.WIDTH, TabsContentSlotCheckBoxInput.HEIGHT);

        this.addComponent(this.renderedComponent);
    }

    @Override
    protected void onClick() {
        this.renderedComponent.setMaterial(this.renderedComponent.getMaterial() == TabsContentSlotCheckBoxInput.MATERIAL_YES ? TabsContentSlotCheckBoxInput.MATERIAL_NO : TabsContentSlotCheckBoxInput.MATERIAL_YES);
    }

}