package stardeath.world.tiles;

import stardeath.world.visitors.TileVisitor;

public class Armory extends Regular {

  public Armory(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
