package stardeath;

import stardeath.world.Vector;

/**
 * An Entity represents something that is on the map
 */
public abstract class Entity {

  protected Vector position;

  protected Entity(Vector vector) {
    this.position = vector;
  }

  /**
   * Get the position of the entity
   * @return A Vector representing the position of the entity
   */
  public Vector getPosition() {
    return position;
  }
}
