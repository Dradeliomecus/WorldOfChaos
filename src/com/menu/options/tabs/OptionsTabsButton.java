package com.menu.options.tabs;

import engine.game.objects.button.ButtonStyle;
import engine.game.objects.tab.Tabs;
import engine.game.objects.tab.TabsButton;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

abstract public class OptionsTabsButton extends TabsButton {

    /**
     * OptionsTabsButton's y position.
     */
    final public static float Y_POS = 0.96875f;

    /**
     * OptionsTabsButton's width.
     */
    final public static float WIDTH = 0.293103f * Window.getRatio();

    /**
     * OptionsTabsButton's height.
     */
    final public static float HEIGHT = 0.1640625f;

    /**
     * Creates a new OptionsTabsButton's instance.
     *
     * @param parent Tabs' parent
     * @param index Tab's index
     * @param name Tab's name
     */
    public OptionsTabsButton(final Tabs parent, final int index, final String name) {
        super(parent, index, OptionsTabsButton.createButtonStyle(name));

        this.setOverrideGlobalActivation((byte) 1);
    }

    /**
     * Creates a new ButtonStyle for an Options' Tabs button.
     *
     * @param name Tab's name
     * @return new ButtonStyle
     */
    private static ButtonStyle createButtonStyle(final String name) {
        return new ButtonStyle(
            new Material(new Texture("/menu/optionsPanel/tabs/button-" + name)),
            new Material(new Texture("/menu/optionsPanel/tabs/button-" + name + "-over")),
            new Material(new Texture("/menu/optionsPanel/tabs/button-" + name + "-onclick")),
            new Material(new Texture("/menu/optionsPanel/tabs/button-" + name)),
            OptionsTabsButton.WIDTH,
            OptionsTabsButton.HEIGHT
        );
    }

}