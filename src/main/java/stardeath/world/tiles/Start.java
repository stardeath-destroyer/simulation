package stardeath.world.tiles;

import stardeath.world.visitors.TileVisitor;

public class Start extends Regular {

  public Start(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    super.accept(visitor);
    visitor.visitTile(this);
  }
}
