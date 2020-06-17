package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;

/**
 * Abstract elevator
 */
public abstract class Elevator extends Tile {

  public Elevator(Vector vector) {
    super(vector, false);
  }
}
