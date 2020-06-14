package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class Terminal extends Tile {
  private boolean online;

  public Terminal(Vector vector) {
    super(vector, false);
    online = true;
  }

  public void destroy() {
    online = false;
  }

  public boolean isOnline() {
    return online;
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
