package stardeath.world;

import stardeath.Entity;

public abstract class Tile extends Entity {

  public abstract void accept(TileVisitor visitor);
}
