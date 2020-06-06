package stardeath.world.tiles;

import stardeath.EntityVisitor;
import stardeath.world.Tile;

public class Regular extends Tile {

  @Override
  public void accept(EntityVisitor visitor) {
    visitor.visitEntity(this);
  }
}
