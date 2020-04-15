package engine.oldPhysic;

import java.util.ArrayList;

final public class OldPhysicsEngine {

	/**
	 * PhysicsEngine's objects.
	 */
	final private ArrayList<OldPhysicsObject> objects;

	/**
	 * Creates a new PhysicsEngine's instance.
	 */
	public OldPhysicsEngine() {
		this.objects = new ArrayList<>();
	}

	/**
	 * Adds a PhysicsObject to the PhysicsEngine.
	 *
	 * @param object PhysicsObject to set
	 */
	final public void addObject(final OldPhysicsObject object) {
		this.getObjects().add(object);
	}

	/**
	 * Simulates the physics between all the PhysicsObjects.
	 *
	 * @param delta How much time should it be simulating ?
	 */
	final public void simulate(final double delta) {
		for(final OldPhysicsObject object : this.getObjects()) {
			object.integrate(delta);
		}
	}

	/**
	 * Handles collisions between all PhysicsEngine's object.
	 */
	final public void handleCollisions() {
		for(int i = 0; i < this.getNumObjects(); i++) {
			for(int j = i + 1; j < this.getNumObjects(); j++) {
				final OldIntersectData intersectData = this.getObject(i).getCollider().intersect(this.getObject(j).getCollider());

				if(intersectData.doesIntersect()) {
					this.getObject(i).setVelocity(this.getObject(i).getVelocity().mul(-1));
					this.getObject(j).setVelocity(this.getObject(j).getVelocity().mul(-1));
				}
			}
		}
	}

	/**
	 * Returns the PhysicsEngine's list of objects.
	 *
	 * @return PhysicsEngine.objects
	 */
	private ArrayList<OldPhysicsObject> getObjects() {
		return this.objects;
	}

	/**
	 * Returns the #index PhysicsEngine's object.
	 *
	 * @param index Object index in the list
	 * @return PhysicsEngine.objects[index]
	 */
	private OldPhysicsObject getObject(final int index) {
		try {
			return this.getObjects().get(index);
		} catch(final ArrayIndexOutOfBoundsException e) {
			System.err.println("Error: index " + index + " does not exists in PhysicsEngine.objects, max size: " + this.getNumObjects());
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}

	/**
	 * Returns the number of objects in the list.
	 *
	 * @return PhysicsEngine.objects.size
	 */
	private int getNumObjects() {
		return this.getObjects().size();
	}

}