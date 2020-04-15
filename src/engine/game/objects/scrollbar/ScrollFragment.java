package engine.game.objects.scrollbar;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;

public class ScrollFragment extends GameObject {

	/**
	 * ScrollFragment's rendered component.
	 */
	private RenderedComponent renderedComponent;

	/**
	 * Creates a new ScrollFragment's instance.
	 *
	 * @param name ScrollFragment's name
	 * @param width ScrollFragment's width
	 * @param height ScrollFragment's height
	 * @param renderedComponent ScrollFragment's rendered component
	 */
	public ScrollFragment(final String name, final float width, final float height, final RenderedComponent renderedComponent) {
		super("Scroll Fragment " + name, width, height);

		this.setRenderedComponent(renderedComponent);
	}

	/**
	 * Returns the ScrollFragment's rendered component.
	 *
	 * @return ScrollFragment.renderedComponent
	 */
	final protected RenderedComponent getRenderedComponent() {
		return this.renderedComponent;
	}

	/**
	 * Sets the ScrollFragment's rendered component.
	 *
	 * @param renderedComponent Rendered component to set
	 */
	final protected void setRenderedComponent(final RenderedComponent renderedComponent) {
		if(this.getRenderedComponent() != null) {
			this.removeComponent(this.getRenderedComponent());
		}

		this.renderedComponent = renderedComponent;
		this.addComponent(renderedComponent);
	}

}