package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.visitors.TileVisitor;

public class Wall extends Tile {

  public Wall(int x, int y) {
    super(x, y, true);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
