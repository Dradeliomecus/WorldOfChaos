package com.menu.options.tabs.content.slot.key;

import com.GameOptions;
import com.menu.options.tabs.content.slot.TabsContentSlotInput;
import engine.game.components.RenderedComponent;
import engine.math.Vector2f;
import engine.rendering.texture.Animation;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Input;
import engine.util.Window;

class TabsContentSlotKeyInput extends TabsContentSlotInput {

    /**
     * TabsContentSlotKeyInput's x position.
     */
    final static float X_POS = 0.3577586f * Window.getRatio();

    /**
     * TabsContentSlotKeyInput's width.
     */
    final public static float WIDTH = 0.1853448275862f * Window.getRatio();

    /**
     * TabsContentSlotKeyInput's height.
     */
    final public static float HEIGHT = 0.0859375f;

    /**
     * TabsContentSlotKeyInput's material while selected.
     */
    final public static Material SELECTED_MATERIAL = new Material(new Animation(new Texture[] {new Texture("/textBitmaps/menuHotKeys/underscore"), new Texture("null1x1")}, 1));

    /**
     * TabsContentSlotKeyInput's rendered component.
     */
    final private RenderedComponent renderedComponent;

    /**
     * Creates a new TabsContentSlotKeyInput instance.
     *
     * @param index Index of the key in the GameOptions
     */
    TabsContentSlotKeyInput(final String index) {
        super("Options Tabs key input", TabsContentSlotKeyInput.WIDTH, TabsContentSlotKeyInput.HEIGHT);

        this.setPosition(new Vector2f(TabsContentSlotKeyInput.X_POS, 0));
        this.setDepth(-0.01f);

        this.renderedComponent = new RenderedComponent(TabsContentSlotKeyInput.createMaterial(GameOptions.getKey(index).get()), TabsContentSlotKeyInput.WIDTH, TabsContentSlotKeyInput.HEIGHT);
        this.addComponent(this.renderedComponent);
    }

    /**
     * Sets the rendered component according to the key code.
     *
     * @param keyValue Key code
     */
    final void setRenderedComponent(final int keyValue) {
        this.renderedComponent.setMaterial(TabsContentSlotKeyInput.createMaterial(keyValue));
    }

    /**
     * Sets the input selected.
     */
    final void setSelected() {
        this.renderedComponent.setMaterial(TabsContentSlotKeyInput.SELECTED_MATERIAL);
    }

    /**
     * Returns a new Material for a specific key value.
     *
     * @param keyValue Key to print on the Material
     * @return new Material
     */
    private static Material createMaterial(final int keyValue) {
        final String folder;
        final int code;

        if(keyValue < Input.NUM_KEYCODES) {
            folder = "menuHotKeys";
            code = keyValue;
        } else {
            folder = "menuMouseButtons";
            code = keyValue - Input.NUM_KEYCODES;
        }

        if(support.File.getImage("/media/texture/textBitmaps/" + folder + "/" + code + ".png", false) == null) {
            System.out.println("This file does not exists, and should: /media/texture/textBitmaps/" + folder + "/" + code + ".png");
            return new Material(new Texture("/textBitmaps/menuHotKeys/unknown"));
        } else {
            return new Material(new Texture("/textBitmaps/" + folder + "/" + code));
        }
    }

}