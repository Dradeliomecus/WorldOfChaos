package com.menu.options.tabs.audio;

import com.menu.options.tabs.OptionsTabsButton;
import engine.game.objects.tab.Tabs;
import engine.math.Vector2f;
import engine.util.Window;

public class AudioTabsButton extends OptionsTabsButton {

    /**
     * AudioTabsButton's x position.
     */
    final static float X_POS = 0.5301724f * Window.getRatio();

    /**
     * Creates a new AudioTabsButton instance.
     *
     * @param parent Tabs parent
     */
    public AudioTabsButton(final Tabs parent) {
        super(parent, 1, "audio");

        this.setPosition(new Vector2f(AudioTabsButton.X_POS, OptionsTabsButton.Y_POS));
    }

}