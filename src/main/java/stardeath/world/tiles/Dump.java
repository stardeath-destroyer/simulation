package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class Dump extends Regular {

  public Dump(Vector vector) {
    super(vector);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }

}
