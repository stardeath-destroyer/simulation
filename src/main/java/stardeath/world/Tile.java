package stardeath.world;

import stardeath.Entity;

public abstract class Tile extends Entity {

  protected Tile(int x, int y) {
    super(x, y);
  }

  public abstract void accept(TileVisitor visitor);
}
