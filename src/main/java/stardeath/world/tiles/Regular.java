package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.TileVisitor;

public class Regular extends Tile {

  public Regular(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
