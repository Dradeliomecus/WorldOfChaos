package com.menu.options.tabs.content.scrollbar;

import engine.game.components.RenderedComponent;
import engine.game.objects.scrollbar.ScrollFragment;
import engine.math.Vector2f;
import engine.rendering.Mesh;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;

class TabsScrollBottomFragment extends ScrollFragment {

    /**
     * Creates a new TabsScrollBottomFragment instance.
     */
    TabsScrollBottomFragment() {
        super("Scroll's bottom fragment", TabsScrollTopFragment.WIDTH, TabsScrollTopFragment.HEIGHT, new RenderedComponent(Mesh.createWithInvertedTexture(TabsScrollTopFragment.WIDTH, TabsScrollTopFragment.HEIGHT), new Material(new Texture("/menu/optionsPanel/tabs/scrollbar/scroll-top")), TabsScrollTopFragment.WIDTH, TabsScrollTopFragment.HEIGHT));

        this.setPosition(new Vector2f(TabsScrollTopFragment.X_POS, 0));
    }

}