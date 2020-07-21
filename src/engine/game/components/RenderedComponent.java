package engine.game.components;

import engine.rendering.texture.Material;
import engine.rendering.Mesh;
import engine.rendering.shader.Shader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class RenderedComponent extends GameComponent {

	/**
	 * RenderedComponent's mesh.
	 */
	private @NotNull Mesh mesh;

	/**
	 * RenderedComponent's material.
	 */
	private @NotNull Material material;

	/**
	 * True = component is rendered later (e.g. because of 0 < opacity < 1).
	 */
	private boolean renderedLater;

	/**
	 * Creates a new RenderedComponent instance with a basic mesh width * height.
	 *
	 * @param material Material to set
	 * @param width Width to set
	 * @param height Height to set
	 */
	public RenderedComponent(final @NotNull Material material, final float width, final float height){
		this(Mesh.create(width, height), material, width, height);
	}

	/**
	 * Creates a new RenderedComponent instance with a basic mesh width * height.
	 *
	 * @param material Material to set
	 * @param width Width to set
	 * @param height Height to set
	 * @param renderedLater Is the component rendered later?
	 */
	public RenderedComponent(final @NotNull Material material, final float width, final float height, final boolean renderedLater){
		this(Mesh.create(width, height), material, width, height, renderedLater);
	}

	/**
	 * Creates a new RenderedComponent instance.
	 *
	 * @param mesh Mesh to set
	 * @param material Material to set
	 * @param width Width to set
	 * @param height Height to set
	 */
	public RenderedComponent(final @NotNull Mesh mesh, final @NotNull Material material, final float width, final float height){
		this(mesh, material, width, height, false);
	}

	/**
	 * Creates a new RenderedComponent instance.
	 *
	 * @param mesh Mesh to set
	 * @param material Material to set
	 * @param width Width to set
	 * @param height Height to set
	 * @param renderedLater Is the component rendered later?
	 */
	public RenderedComponent(final @NotNull Mesh mesh, final @NotNull Material material, final float width, final float height, final boolean renderedLater){
		super(width, height);
		this.set(mesh, material);

		this.renderedLater = renderedLater;
	}

	@Override
	public void update(final double delta) {
		this.getMaterial().update(delta);
	}

	/**
	 * Returns the RenderedComponent's Material.
	 *
	 * @return RenderedComponent.material
	 */
	@Contract(pure = true)
	final public @NotNull Material getMaterial(){
		return this.material;
	}

	/**
	 * Returns whether the RenderedComponent has to be rendered later or not.
	 *
	 * @return RenderedComponent.renderedLater
	 */
	@Contract(pure = true)
	final public boolean isRenderedLater() {
		return this.renderedLater;
	}

	/**
	 * Sets the RenderedComponent's value.
	 *
	 * @param mesh Mesh to set
	 * @param material Material to set
	 */
	final public void set(final @NotNull Mesh mesh, final Material material){
		this.setMesh(mesh);
		this.setMaterial(material);
	}

	/**
	 * Sets the RenderedComponent's Mesh.
	 *
	 * @param mesh Mesh to set
	 */
	final public void setMesh(final @NotNull Mesh mesh){
		this.mesh = mesh;
	}

	/**
	 * Sets the RenderedComponent's Material.
	 *
	 * @param material Material to set
	 */
	final public void setMaterial(final @NotNull Material material){
		this.material = material;
	}

	@Override
	public void render(final @NotNull Shader shader, final @Nullable ArrayList<GameComponent> renderLater) {
		if(!this.renderedLater || renderLater == null) {
			shader.bind();
			shader.updateUniforms(this.getMaterial(), this.getTransform());
			this.mesh.draw();
		} else {
			renderLater.add(this);
		}
	}

}