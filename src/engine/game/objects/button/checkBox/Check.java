package engine.game.objects.button.checkBox;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.rendering.texture.Material;
import org.jetbrains.annotations.NotNull;

public class Check extends GameObject {

	/**
	 * Is the check yes or no.
	 */
	private byte status;

	/**
	 * Check's materials
	 * 0 = yes
	 * 1 = no
	 * 2 = half.
	 */
	private @NotNull Material[] materials;

	/**
	 * Check's rendered component.
	 */
	final private @NotNull RenderedComponent renderedComponent;

	/**
	 * Creates a new Check instance.
	 *
	 * @param name Check's name
	 * @param width Check's width
	 * @param height Check's height
	 * @param materials Check's materials
	 * @param status Check's status
	 */
	public Check(final String name, final float width, final float height, @NotNull final Material[] materials, final byte status) {
		super("Check " + name, width, height);

		if(status < 0 || status > 2) {
			System.err.println("Error: Status must be between 0 and 2.");
			System.err.println("Status given: " + status);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.status = status;
		this.renderedComponent = new RenderedComponent(materials[status], width, height);
		this.setMaterials(materials);

		this.addComponent(this.renderedComponent);
	}

	/**
	 * Returns the Check's status.
	 *
	 * @return Check.status
	 */
	final protected byte getStatus() {
		return this.status;
	}

	/**
	 * Returns all of the Check's materials.
	 *
	 * @return Check.materials
	 */
	private @NotNull Material[] getMaterials() {
		return this.materials;
	}

	/**
	 * Returns a specific Check's material.
	 *
	 * @param index Material's index
	 * @return Check.materials[index]
	 */
	private @NotNull Material getMaterial(final byte index) {
		return this.getMaterials()[index];
	}

	/**
	 * Sets the Check's status.
	 *
	 * @param status Status to set
	 */
	final protected void setStatus(final byte status) {
		if(status < 0 || status > 2) {
			System.err.println("Error: Status must be between 0 and 2.");
			System.err.println("Status given: " + status);
			new Exception().printStackTrace();
			System.exit(1);
		}

		try {
			this.getMaterial(status);
		} catch(final IndexOutOfBoundsException e) {
			System.err.println("Error, status does not have a rendered component.");
			e.printStackTrace();
			System.exit(1);
		}

		this.renderedComponent.setMaterial(this.getMaterial(status));

		this.status = status;
	}

	/**
	 * Sets the Check's materials.
	 *
	 * @param materials Material to set
	 */
	final protected void setMaterials(final @NotNull Material[] materials) {
		if(materials.length > 2) {
			System.err.println("Error: The number of rendered components must not exceed 2.");
			System.err.println("Length given: " + materials.length);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.materials = materials;
	}

	/**
	 * Sets a Check's specific material.
	 *
	 * @param material material to set
	 * @param index Rendered component's index
	 */
	final protected void setMaterial(final @NotNull Material material, final byte index) {
		if(index < 0 || index > 2) {
			System.err.println("Error: Index must be between 0 and 2.");
			System.err.println("Index given: " + index);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.materials[index] = material;
	}

}