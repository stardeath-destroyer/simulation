package stardeath.world.tiles;

import stardeath.world.TileVisitor;

public class Dump extends Regular {

  public Dump(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }

}
