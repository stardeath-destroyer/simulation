package stardeath.world;

import stardeath.Entity;
import stardeath.world.visitors.TileVisitor;

public abstract class Tile extends Entity {

  private boolean discovered;
  private final boolean opaque;

  protected Tile(int x, int y, boolean opaque) {
    super(x, y);
    this.discovered = false;
    this.opaque = opaque;
  }

  public final void unveil() {
    this.discovered = true;
  }

  public final boolean isDiscovered() {
    return discovered;
  }

  public final boolean isOpaque() {
    return opaque;
  }

  public int distanceTo(int x, int y) {
    int deltaX = this.getX() - x;
    int deltaY = this.getY() - y;
    return (int) Math.ceil(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  public abstract void accept(TileVisitor visitor);
}
