package engine.game.objects.button;

import engine.rendering.texture.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class ButtonStyle {

	/**
	 * Button's width.
	 */
	private float width;

	/**
	 * Button's height.
	 */
	private float height;

	/**
	 * Button's materials (basic, over, click, off).
	 */
	final private @NotNull HashMap<String, Material> materials;

	/**
	 * Creates a new ButtonStyle instance.
	 *
	 * @param width Button's width
	 * @param height Button's height
	 */
	private ButtonStyle(final float width, final float height) {
		this.materials = new HashMap<>();

		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * Creates a new ButtonStyle instance.
	 *
	 * @param basicMaterial Button's basic material
	 * @param overMaterial Button's material when mouse over
	 * @param clickMaterial Button's material when clicked
	 * @param width Button's width
	 * @param height Button's height
	 */
	public ButtonStyle(final @NotNull Material basicMaterial, final Material overMaterial, final Material clickMaterial, final float width, final float height) {
		this(basicMaterial, overMaterial, clickMaterial, null, null, width, height);
	}

	/**
	 * Creates a new ButtonStyle instance.
	 *
	 * @param basicMaterial Button's basic material
	 * @param overMaterial Button's material when mouse over
	 * @param clickMaterial Button's material when clicked
	 * @param offMaterial Button's material when off
	 * @param width Button's width
	 * @param height Button's height
	 */
	public ButtonStyle(final @NotNull Material basicMaterial, @NotNull final Material overMaterial, @NotNull final Material clickMaterial, @Nullable final Material offMaterial, final float width, final float height) {
		this(basicMaterial, overMaterial, clickMaterial, offMaterial, null, width, height);
	}

	/**
	 * Creates a new ButtonStyle instance.
	 *
	 * @param basicMaterial Button's basic material
	 * @param overMaterial Button's material when mouse over
	 * @param clickMaterial Button's material when clicked
	 * @param offMaterial Button's material when off
	 * @param selectedMaterial Button's material when selected
	 * @param width Button's width
	 * @param height Button's height
	 */
	public ButtonStyle(final @NotNull Material basicMaterial, @NotNull final Material overMaterial, final @NotNull Material clickMaterial, final @Nullable Material offMaterial, @Nullable final Material selectedMaterial, final float width, final float height) {
		this(width, height);

		this.setMaterial("basic", basicMaterial);
		this.setMaterial("over", overMaterial);
		this.setMaterial("click", clickMaterial);
		this.setMaterial("off", offMaterial);
		this.setMaterial("selected", selectedMaterial);
	}

	/**
	 * Returns the Button's width.
	 *
	 * @return ButtonStyle.width
	 */
	final public float getWidth() {
		return this.width;
	}

	/**
	 * Returns the Button's height.
	 *
	 * @return ButtonStyle.height
	 */
	final public float getHeight() {
		return this.height;
	}

	/**
	 * Returns a specific Button's material.
	 *
	 * @param index Material's name to search for
	 * @return ButtonStyle.materials[index]
	 */
	final protected Material getMaterial(final @NotNull String index) {
		return this.materials.get(index);
	}

	/**
	 * Returns of the Button has a material for the index.
	 *
	 * @param index Index to check for
	 * @return ButtonStyle.materials[index] != null
	 */
	final public boolean hasMaterial(final @NotNull String index) {
		return this.materials.get(index) != null;
	}

	/**
	 * Returns the Button's material when nothing is happening.
	 *
	 * @return ButtonStyle.materials["basic"]
	 */
	final public @NotNull Material getBasicMaterial() {
		return this.getMaterial("basic");
	}

	/**
	 * Returns the Button's material when the mouse is over the button (but not clicking).
	 *
	 * @return ButtonStyle.materials["over"]
	 */
	final public @NotNull Material getOverMaterial() {
		return this.getMaterial("over");
	}

	/**
	 * Returns the Button's material when the button is being clicked.
	 *
	 * @return ButtonStyle.materials["click"]
	 */
	final public @NotNull Material getClickMaterial() {
		return this.getMaterial("click");
	}

	/**
	 * Returns the Button's material when the button's off.
	 *
	 * @return ButtonStyle.materials["off"]
	 */
	final public @Nullable Material getOffMaterial() {
		return this.getMaterial("off");
	}

	/**
	 * Returns the Button's material when the button is selected.
	 *
	 * @return ButtonStyle.materials["selected"]
	 */
	final public @Nullable Material getSelectedMaterial() {
		return this.getMaterial("selected");
	}

	/**
	 * Sets the Button's width.
	 *
	 * @param width Width to set (in OpenGL units)
	 */
	private void setWidth(final float width) {
		this.width = width;
	}

	/**
	 * Sets the Button's height.
	 *
	 * @param height Height to set (in OpenGL units)
	 */
	private void setHeight(final float height) {
		this.height = height;
	}

	/**
	 * Sets a specific Button's material.
	 *
	 * @param index Material's name to set
	 * @param material Material to set
	 */
	final protected void setMaterial(final @NotNull String index, final @Nullable Material material) {
		this.materials.put(index, material);
	}

}