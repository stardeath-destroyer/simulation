package stardeath.world.tiles;

import stardeath.world.TileVisitor;

public class Hole extends Regular {

  public Hole(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
