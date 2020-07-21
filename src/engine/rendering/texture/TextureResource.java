package engine.rendering.texture;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public class TextureResource {

    /**
     * Buffers to delete.
     */
    final public static ArrayList<Integer> buffersToDelete = new ArrayList<>();

    /**
     * FBOs to delete.
     */
    final public static ArrayList<Integer> fboToDelete = new ArrayList<>();

    /**
     * Texture's id.
     */
    final private int id;

    /**
     * Texture's frame buffer object.
     */
    final private int fbo;

    /**
     * Texture's width.
     */
    final private int width;

    /**
     * Texture's height.
     */
    final private int height;

    /**
     * Texture's attachment.
     */
    final private int attachment;

    /**
     * How many time is this texture used.
     */
    private int referenceCount;

    /**
     * Creates a new TextureResource instance.
     *
     * @param id Texture's id
     * @param fbo Texture's fbo
     * @param width Texture's width (in px)
     * @param height Texture's height (in px).
     * @param attachment Texture's attachment
     */
    TextureResource(final int id, final int fbo, final int width, final int height, final int attachment) {
        this.id = id;
        this.fbo = fbo;
        this.width = width;
        this.height = height;
        this.attachment = attachment;
        this.referenceCount = 1;
    }

    @Override
    final protected void finalize() {
        try {
            super.finalize();
        } catch(final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }

        this.destroy();
    }

    /**
     * Destroy the texture from the memory.
     */
    final void destroy() {
        TextureResource.buffersToDelete.add(this.getID());
        TextureResource.fboToDelete.add(this.getFBO());
    }

    /**
     * Returns the Texture's id.
     *
     * @return TextureResource.id
     */
    @Contract(pure = true)
    final int getID() {
        return this.id;
    }

    /**
     * Returns the Texture's width.
     *
     * @return TextureResource.width
     */
    @Contract(pure = true)
    final int getWidth() {
        return this.width;
    }

    /**
     * Returns the Texture's height.
     *
     * @return TextureResource.height
     */
    @Contract(pure = true)
    final int getHeight() {
        return this.height;
    }

    /**
     * Returns the Texture's fbo.
     *
     * @return TextureResource.fbo
     */
    @Contract(pure = true)
    final int getFBO() {
        return this.fbo;
    }

    /**
     * Returns the Texture's attachment.
     *
     * @return TextureResource.attachment
     */
    @Contract(pure = true)
    final int getAttachment() {
        return this.attachment;
    }

    /**
     * Adds a reference to this resource.
     */
    final void addReference() {
        this.referenceCount++;
    }

    /**
     * Removes a reference to this resource.
     *
     * @return true if TextureResource.referenceCount == 0
     */
    final boolean removeReference() {
        this.referenceCount--;

        return this.referenceCount == 0;
    }

}