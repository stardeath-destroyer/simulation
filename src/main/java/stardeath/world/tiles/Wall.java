package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.TileVisitor;

public class Wall extends Tile {

  public Wall(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
