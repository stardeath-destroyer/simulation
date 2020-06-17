package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

/**
 * A terminal is used to control the stardeath
 */
public class Terminal extends Tile {
  private boolean online;

  public Terminal(Vector vector) {
    super(vector, false);
    online = true;
  }

  /**
   * Destroys the terminal
   */
  public void destroy() {
    online = false;
  }

  /**
   * If the terminal is online, true will be returned
   * @return True if the terminal is online
   */
  public boolean isOnline() {
    return online;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
