package stardeath.world.tiles;

import stardeath.EntityVisitor;
import stardeath.world.Tile;

public class Wall extends Tile {

  @Override
  public void accept(EntityVisitor visitor) {
    visitor.visitEntity(this);
  }
}
