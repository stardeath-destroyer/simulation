package stardeath.world;

import stardeath.Entity;
import stardeath.world.visitors.TileVisitor;

/**
 * Represents a Tile
 */
public abstract class Tile extends Entity {

  private boolean discovered;
  private final boolean opaque;

  protected Tile(Vector position, boolean opaque) {
    super(position);
    this.discovered = false;
    this.opaque = opaque;
  }

  /**
   * Discovers the tile to the player
   */
  public final void unveil() {
    this.discovered = true;
  }

  /**
   * Returns true if the tile has been discovered by the player
   * @return True if the tile has been discovered by the player
   */
  public final boolean isDiscovered() {
    return discovered;
  }

  /**
   * Returns true if the tile is opaque (i.e. we cannot see through it)
   * @return True if the tile is opaque (i.e. we cannot see through it)
   */
  public final boolean isOpaque() {
    return opaque;
  }

  /**
   * Accepts a given visitor
   * @param visitor The visitor that will be accepted
   */
  public abstract void accept(TileVisitor visitor);
}
