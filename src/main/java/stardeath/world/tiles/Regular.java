package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

/**
 * Normal tile
 */
public class Regular extends Tile {

  public Regular(Vector vector) {
    super(vector, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
