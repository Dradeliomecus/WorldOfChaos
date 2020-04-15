package com.menu.options.tabs.controls;

import com.menu.options.tabs.OptionsTabsButton;
import engine.game.objects.tab.Tabs;
import engine.math.Vector2f;
import engine.util.Window;

public class ControlsTabsButton extends OptionsTabsButton {

    /**
     * ControlsTabsButton's x position.
     */
    final static float X_POS = 1.0302f * Window.getRatio();

    /**
     * Creates a new ControlsTabsButton instance.
     *
     * @param parent Tabs parent
     */
    public ControlsTabsButton(final Tabs parent) {
        super(parent, 2, "controls");

        this.setPosition(new Vector2f(ControlsTabsButton.X_POS, OptionsTabsButton.Y_POS));
    }

}