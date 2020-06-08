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

  public abstract void accept(TileVisitor visitor);
}
