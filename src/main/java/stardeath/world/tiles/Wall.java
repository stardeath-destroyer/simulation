package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.TileVisitor;

public abstract class Wall extends Tile {

  public Wall(int x, int y) {
    super(x, y);
  }
}
