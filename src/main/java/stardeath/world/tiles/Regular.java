package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.TileVisitor;

public class Regular extends Tile {

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
