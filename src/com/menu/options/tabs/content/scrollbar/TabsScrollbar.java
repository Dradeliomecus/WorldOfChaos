package com.menu.options.tabs.content.scrollbar;

import engine.game.objects.button.ButtonStyle;
import engine.game.objects.scrollbar.GameObjectScrollable;
import engine.game.objects.scrollbar.Scrollbar;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

public class TabsScrollbar extends Scrollbar {

    /**
     * TabsScrollbar's x position.
     */
    final public static float X_POS = 1.3275862f * Window.getRatio();

    /**
     * TabsScrollbar's y position.
     */
    final public static float Y_POS = 0.015625f;

    /**
     * TabsScrollbar's with.
     */
    final public static float WIDTH = 0.034483f * Window.getRatio();

    /**
     * TabsScrollbar's height.
     */
    final public static float HEIGHT = 0.890625f;

    /**
     * Scrollbar's style.
     */
    final public static ButtonStyle STYLE = new ButtonStyle(
        new Material(new Texture("/menu/optionsPanel/tabs/scrollbar/scrollbar")),
        new Material(new Texture("/menu/optionsPanel/tabs/scrollbar/scrollbar-over")),
        new Material(new Texture("/menu/optionsPanel/tabs/scrollbar/scrollbar-onclick")),
        new Material(new Texture("/menu/optionsPanel/tabs/scrollbar/scrollbar")),
        TabsScrollbar.WIDTH, TabsScrollbar.HEIGHT
    );

    /**
     * Creates a new TabsScrollbar instance.
     *
     * @param parent Parent that the scrollbar refers to
     * @param scroll TabsScrollbar's scroll
     */
    public TabsScrollbar(final GameObjectScrollable parent, final TabsScroll scroll) {
        super("Tabs Scrollbar for " + parent.toString(), TabsScrollbar.STYLE, scroll, parent);

        this.setPosition(new Vector2f(TabsScrollbar.X_POS, TabsScrollbar.Y_POS));
        this.setDepth(-0.05f);
        this.setOverrideGlobalActivation((byte) 1);
    }

}