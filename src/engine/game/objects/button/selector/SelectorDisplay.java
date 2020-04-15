package engine.game.objects.button.selector;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import org.jetbrains.annotations.NotNull;

public class SelectorDisplay extends GameObject {

    /**
     * SelectorDisplay's materials.
     */
    private @NotNull Material[] materials;

    /**
     * SelectorDisplay's rendered component.
     */
    final private @NotNull RenderedComponent renderedComponent;

    /**
     * Creates a new SelectorDisplay instance.
     *
     * @param name SelectorDisplay's name
     * @param materials SelectorDisplay's materials
     * @param width SelectorDisplay's width
     * @param height SelectorDisplay's height
     * @param pos SelectorDisplay's position
     * @param index Index to start with
     */
    public SelectorDisplay(final @NotNull String name, final @NotNull Material[] materials, final float width, final float height, final @NotNull Vector2f pos, final int index) {
        super(name, width, height);

        this.setPosition(pos);

        this.renderedComponent = new RenderedComponent(materials[index], width, height);
        this.addComponent(this.getRenderedComponent());

        this.setMaterials(materials);
    }

    /**
     * Returns the SelectorDisplay's materials.
     *
     * @return SelectorDisplay.materials
     */
    final protected @NotNull Material[] getMaterials() {
        return this.materials;
    }

    /**
     * Returns a specific SelectorDisplay's material.
     *
     * @param index Material's index
     * @return SelectorDisplay.materials[index]
     */
    final protected @NotNull Material getMaterial(final int index) {
        return this.getMaterials()[index];
    }

    /**
     * Returns the SelectorDisplay's rendered component.
     *
     * @return SelectorDisplay.renderedComponent
     */
    final protected @NotNull RenderedComponent getRenderedComponent() {
        return this.renderedComponent;
    }

    /**
     * Sets the Selector's materials.
     *
     * @param materials Materials to set
     */
    final protected void setMaterials(final @NotNull Material[] materials) {
        this.materials = materials;
    }

    /**
     * Sets the correct Material corresponding to the index.
     *
     * @param index Index to use
     */
    final public void setIndex(final int index) {
        this.getRenderedComponent().setMaterial(this.getMaterial(index));
    }

}