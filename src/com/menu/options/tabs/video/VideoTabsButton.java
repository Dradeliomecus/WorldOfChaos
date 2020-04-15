package com.menu.options.tabs.video;

import com.menu.options.tabs.OptionsTabsButton;
import engine.game.objects.tab.Tabs;
import engine.math.Vector2f;
import engine.util.Window;

public class VideoTabsButton extends OptionsTabsButton {

    /**
     * VideoTabsButton's x position.
     */
    final static float X_POS = 0.043103f * Window.getRatio();

    /**
     * Creates a new VideoTabsButton instance.
     *
     * @param parent Tabs parent
     */
    public VideoTabsButton(final Tabs parent) {
        super(parent, 0, "video");

        this.setPosition(new Vector2f(VideoTabsButton.X_POS, Y_POS));
    }

}