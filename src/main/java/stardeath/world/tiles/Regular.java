package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class Regular extends Tile {

  public Regular(Vector vector) {
    super(vector, false);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
