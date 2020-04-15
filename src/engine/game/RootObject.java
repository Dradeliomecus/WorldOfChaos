package engine.game;

import engine.game.objects.GameObject;

final public class RootObject extends GameObject {

	/**
	 * Creates the RootObject instance.
	 */
	RootObject() {
		super("Root Object", Float.MAX_VALUE, Float.MAX_VALUE);
	}

	@Override
	final public RootObject init() {
		return this;
	}

	/**
	 * Adds an object as a child of the root object.
	 *
	 * @param child GameObject to add
	 */
	final public void addChildToRootObject(final GameObject child) {
		this.addChildInstantly(child);
	}

	/**
	 * Removes an object from the root object's children list.
	 *
	 * @param child GameObject to remove
	 */
	final public void removeChildFromRootObject(final GameObject child) {
		this.removeChild(child);
	}

}