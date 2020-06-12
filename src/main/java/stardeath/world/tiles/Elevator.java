package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.visitors.TileVisitor;

public abstract class Elevator extends Tile {

  public Elevator(int x, int y) {
    super(x, y, true);
  }
}
