package stardeath;

import stardeath.world.Vector;

public abstract class Entity {

  protected Vector position;

  protected Entity(Vector vector) {
    this.position = vector;
  }

  public Vector getPosition() {
    return position;
  }
}
