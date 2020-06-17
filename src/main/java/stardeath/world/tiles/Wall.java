package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

/**
 * A wall
 */
public class Wall extends Tile {

  public Wall(Vector position) {
    super(position, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
