package com.menu.options.tabs.content.scrollbar;

import engine.game.components.RenderedComponent;
import engine.game.objects.scrollbar.ScrollFragment;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class TabsScrollMiddleFragment extends ScrollFragment {

    /**
     * TabsScrollMiddleFragment's width.
     */
    final static float WIDTH = 0.0258621f * Window.getRatio();

    /**
     * Creates a new TabsScrollMiddleFragment instance.
     *
     * @param scrollbarHeight Height where the scroll can move
     */
    TabsScrollMiddleFragment(final float scrollbarHeight) {
        super("middle options oldTabs", TabsScrollMiddleFragment.WIDTH, scrollbarHeight - 2 * TabsScrollTopFragment.HEIGHT, new RenderedComponent(new Material(new Texture("/menu/optionsPanel/tabs/scrollbar/scroll-middle")), TabsScrollMiddleFragment.WIDTH, scrollbarHeight - 2 * TabsScrollTopFragment.HEIGHT));

        this.setPosition(new Vector2f(0, TabsScrollTopFragment.HEIGHT));
    }

}