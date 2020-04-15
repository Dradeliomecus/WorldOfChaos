package com.menu.options.tabs;

import com.menu.options.tabs.audio.AudioTabsButton;
import com.menu.options.tabs.audio.AudioTabsContent;
import com.menu.options.tabs.controls.ControlsTabsButton;
import com.menu.options.tabs.controls.ControlsTabsContent;
import com.menu.options.tabs.video.VideoTabsButton;
import com.menu.options.tabs.video.VideoTabsContent;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.game.objects.tab.Tabs;
import engine.game.objects.tab.TabsButton;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

public class OptionsTabs extends Tabs {

    /**
     * OptionsPanelQuitButton's x position.
     */
    final public static float X_POS = 0.173f * Window.getRatio();

    /**
     * OptionsPanelQuitButton's y position.
     */
    final public static float Y_POS = 0.22f;

    /**
     * Tabs' width.
     */
    final public static float WIDTH = 1.4f * Window.getRatio();

    /**
     * Tabs' height.
     */
    final public static float HEIGHT = 1.13f;

    /**
     * Creates a new OptionsTabs instance.
     */
    public OptionsTabs() {
        super("options", 3, OptionsTabs.WIDTH, OptionsTabs.HEIGHT);

        this.setPosition(new Vector2f(OptionsTabs.X_POS, OptionsTabs.Y_POS));
        this.setDepth(-0.05f);

        final RenderedComponent[] backgrounds = new RenderedComponent[] {
            new RenderedComponent(new Material(new Texture("/menu/optionsPanel/tabs/background-video")), OptionsTabs.WIDTH, OptionsTabs.HEIGHT),
            new RenderedComponent(new Material(new Texture("/menu/optionsPanel/tabs/background-audio")), OptionsTabs.WIDTH, OptionsTabs.HEIGHT),
            new RenderedComponent(new Material(new Texture("/menu/optionsPanel/tabs/background-controls")), OptionsTabs.WIDTH, OptionsTabs.HEIGHT)
        };
        this.setBackgrounds(backgrounds);

        final GameObject[] contents = new GameObject[] {
            new VideoTabsContent(),
            new AudioTabsContent(),
            new ControlsTabsContent()
        };
        this.setContents(contents);

        final TabsButton[] buttons = new TabsButton[] { new VideoTabsButton(this), new AudioTabsButton(this), new ControlsTabsButton(this) };
        this.setButtons(buttons);
    }

}