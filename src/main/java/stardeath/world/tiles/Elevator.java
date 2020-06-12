package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public abstract class Elevator extends Tile {

  public Elevator(Vector vector) {
    super(vector, false);
  }
}
