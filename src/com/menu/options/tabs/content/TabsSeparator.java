package com.menu.options.tabs.content;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Window;

class TabsSeparator extends GameObject {

    /**
     * Status for no top and no bottom.
     */
    final public static byte STATUS_NONE = 0;

    /**
     * Status for top and no bottom.
     */
    final public static byte STATUS_TOP = 1;

    /**
     * Status for no top and bottom.
     */
    final public static byte STATUS_BOTTOM = 2;

    /**
     * Status for top and bottom.
     */
    final public static byte STATUS_TOP_BOTTOM = 3;

    /**
     * TabsSeparator's x position.
     */
    final public static float X_POS = 0.65086206896552f * Window.getRatio();

    /**
     * TabsSeparator's width.
     */
    final public static float WIDTH = 0.017241379310345f * Window.getRatio();

    /**
     * TabsSeparator's maximum height.
     */
    final public static float MAX_HEIGHT = 0.234375f;

    /**
     * Creates a new TabsSeparator instance.
     *
     * @param height TabsSeparator's height
     * @param yPos TabsSeparator y position
     * @param status TabsSeparator status
     */
    TabsSeparator(final float height, final float yPos, final byte status) {
        super("Options Tabs Separator", TabsSeparator.WIDTH, height);

        this.setPosition(new Vector2f(TabsSeparator.X_POS, yPos));
        this.setDepth(-0.01f);

        final int w = 4;
        final int h = Math.round(height * 128.0f);

        final int[] pixels = new int[w * h];

        for(int i = 0; i < w * h; i++) {
            if(((status == STATUS_TOP || status == STATUS_TOP_BOTTOM) && (i == 0 || i == 3)) || ((status == STATUS_BOTTOM || status == STATUS_TOP_BOTTOM) && (i == w * h - 4 || i == w * h - 1))) {
                pixels[i] = 0x00000000;
                continue;
            }

            if(i % 4 == 0 || i % 4 == 3 || ((status == STATUS_TOP_BOTTOM || status == STATUS_TOP) && (i == 1 || i == 2)) || ((status == STATUS_BOTTOM || status == STATUS_TOP_BOTTOM) && (i == w * h - 2 || i == w * h - 3))) {
                pixels[i] = 0xFF363636;
                continue;
            }

            if(i % 4 == 1 || i % 4 == 2) {
                pixels[i] = 0xFF272727;
                continue;
            }

            System.err.println("Bite !");
            new Exception().printStackTrace();
            System.exit(1);
        }

        final Texture texture = new Texture(w, h, pixels);
        this.addComponent(new RenderedComponent(new Material(texture), TabsSeparator.WIDTH, height));
    }

}