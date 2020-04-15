package com.menu.options.tabs.content.categoryHeader;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.Mesh;
import engine.rendering.Vertex;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class TabsCategoryHeaderDecoration extends GameObject {

    /**
     * TabsCategoryHeaderDecoration's maximum width.
     */
    final public static float MAX_WIDTH = 0.61206896552f * Window.getRatio();

    /**
     * TabsCategoryHeaderDecoration's height.
     */
    final public static float HEIGHT = 0.0859375f;

    /**
     * Creates a new TabsCategoryHeaderDecoration instance.
     *
     * @param width TabsCategoryHeaderDecoration's width
     * @param inverted True = right one, False = left one
     */
    TabsCategoryHeaderDecoration(final float width, final boolean inverted) {
        super("Tabs Category Header Decoration (" + (inverted ? "right" : "left") + ")", width, TabsCategoryHeaderDecoration.HEIGHT);

        this.setPosition(new Vector2f(inverted ? TabsCategoryHeader.WIDTH - width : 0.0f, 0.0f));

        final float widthTextureRatio = this.getWidth() / TabsCategoryHeaderDecoration.MAX_WIDTH;
        final Mesh mesh = new Mesh();
        final Vertex[] vertices = new Vertex[] {
            new Vertex(new Vector2f(0, 0), new Vector2f(inverted ? widthTextureRatio : 0, 1)),
            new Vertex(new Vector2f(0, this.getHeight()), new Vector2f(inverted ? widthTextureRatio : 0, 0)),
            new Vertex(new Vector2f(this.getWidth(), this.getHeight()), new Vector2f(inverted ? 0 : widthTextureRatio, 0)),
            new Vertex(new Vector2f(this.getWidth(), 0), new Vector2f(inverted ? 0 : widthTextureRatio, 1))
        };

        mesh.setVertices(vertices);

        this.addComponent(new RenderedComponent(mesh, new Material(new Texture("/menu/optionsPanel/tabs/header-decoration")), this.getWidth(), this.getHeight()));
    }

}