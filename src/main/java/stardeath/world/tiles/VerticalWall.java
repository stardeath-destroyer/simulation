package stardeath.world.tiles;

import stardeath.world.TileVisitor;

public class VerticalWall extends Wall {

  public VerticalWall(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
