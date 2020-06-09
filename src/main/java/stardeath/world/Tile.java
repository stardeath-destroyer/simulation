package stardeath.world;

import stardeath.Entity;

public abstract class Tile extends Entity {

  private boolean discovered;

  protected Tile(int x, int y) {
    super(x, y);
    this.discovered = false;
  }

  public final void unveil() {
    this.discovered = true;
  }

  public final boolean isDiscovered() {
    return discovered;
  }

  public int distanceTo(int x, int y) {
    int deltaX = this.getX() - x;
    int deltaY = this.getY() - y;
    return (int) Math.ceil(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  public abstract void accept(TileVisitor visitor);
}
