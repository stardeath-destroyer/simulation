package stardeath.world.tiles;

import stardeath.world.TileVisitor;

public class HorizontalWall extends Wall {

  public HorizontalWall(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
