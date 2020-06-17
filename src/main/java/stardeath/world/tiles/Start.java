package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

/**
 * Sort of Tile on which the player can be spawned
 */
public class Start extends Regular {

  public Start(Vector vector) {
    super(vector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(TileVisitor visitor) {
    super.accept(visitor);
    visitor.visitTile(this);
  }
}
