package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class Armory extends Regular {

  public Armory(Vector vector) {
    super(vector);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
