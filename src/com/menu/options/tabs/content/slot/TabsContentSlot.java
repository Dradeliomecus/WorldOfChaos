package com.menu.options.tabs.content.slot;

import com.menu.options.tabs.content.TabsContent;
import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Color;
import engine.util.Window;

abstract public class TabsContentSlot extends Button {

    /**
     * TabsContentButton's width.
     */
    final public static float WIDTH = 0.56034482758621f * Window.getRatio();

    /**
     * TabsContentButton's height.
     */
    final public static float HEIGHT = 0.109375f;

    /**
     * Button's style.
     */
    final static ButtonStyle STYLE = new ButtonStyle(
        new Material(new Texture("null1x1")),
        new Material(new Texture("blank1x1"), new Color(59, 59, 59)),
        new Material(new Texture("blank1x1"), new Color(97, 97, 97)),
        new Material(new Texture("null1x1")),
        new Material(new Texture("blank1x1"), new Color(97, 97, 97)),
        TabsContentSlot.WIDTH, TabsContentSlot.HEIGHT
    );

    /**
     * Index of the key in GameOptions.
     */
    final private String index;

    /**
     * TabsContentSlot's input.
     */
    final private TabsContentSlotInput input;

    /**
     * Pointer to the parent.
     */
    final private TabsContent parent;

    /**
     * Creates a new TabsContentSlot instance.
     *
     * @param label Label to display
     * @param index Index in the GameOptions
     * @param input Input to display
     * @param pos TabsContentButton's position
     * @param contentParent Content where the slot is
     */
    protected TabsContentSlot(final String label, final String index, final TabsContentSlotInput input, final Vector2f pos, final TabsContent contentParent) {
        super("Tabs' content slot " + index, TabsContentSlot.STYLE);

        this.index = index;
        this.input = input;
        this.parent = contentParent;
        this.setPosition(pos);
        this.setDepth(-0.02f);
        this.setOverrideGlobalActivation((byte) 1);

        final TabsContentSlotLabel text = new TabsContentSlotLabel(label).init();
        this.addChild(text);
        this.addChild(input);
    }

    @Override
    public void onClick() {
        super.onClick();

        this.getInput().onClick();
    }

    /**
     * Returns the TabsContentButton index (reference to GameOptions).
     *
     * @return TabsContentButton.index
     */
    final protected String getIndex() {
        return this.index;
    }

    /**
     * Returns the TabsContentSlot's input.
     *
     * @return TabsContentSlot.input
     */
    protected TabsContentSlotInput getInput() {
        return this.input;
    }

    /**
     * Returns the TabsContent parent.
     *
     * @return TabsContentSlot.parent
     */
    final protected TabsContent getParentContent() {
        return this.parent;
    }

}