package com.menu.options.tabs.content.scrollbar;

import engine.game.components.RenderedComponent;
import engine.game.objects.scrollbar.ScrollFragment;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class TabsScrollTopFragment extends ScrollFragment {

    /**
     * TabsScrollTopFragment's x position.
     */
    final static float X_POS = 0.0043103f * Window.getRatio();

    /**
     * TabsScrollTopFragment's width.
     */
    final static float WIDTH = 0.0172414f * Window.getRatio();

    /**
     * TabsScrollTopFragment's height.
     */
    final static float HEIGHT = 0.0234375f;

    /**
     * Creates a new TabsScrollTopFragment instance.
     *
     * @param scrollbarHeight Height where the scroll can move
     */
    TabsScrollTopFragment(final float scrollbarHeight) {
        super("Scroll's top fragment", TabsScrollTopFragment.WIDTH, TabsScrollTopFragment.HEIGHT, new RenderedComponent(new Material(new Texture("/menu/optionsPanel/tabs/scrollbar/scroll-top")), TabsScrollTopFragment.WIDTH, TabsScrollTopFragment.HEIGHT));

        this.setPosition(new Vector2f(TabsScrollTopFragment.X_POS, scrollbarHeight - TabsScrollTopFragment.HEIGHT));
    }

}