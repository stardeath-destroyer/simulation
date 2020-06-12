package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class Start extends Regular {

  public Start(Vector vector) {
    super(vector);
  }

  @Override
  public void accept(TileVisitor visitor) {
    super.accept(visitor);
    visitor.visitTile(this);
  }
}
