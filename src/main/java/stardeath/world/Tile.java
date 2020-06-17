package stardeath.world;

import stardeath.Entity;
import stardeath.world.visitors.TileVisitor;

public abstract class Tile extends Entity {

  private boolean discovered;
  private final boolean opaque;

  protected Tile(Vector position, boolean opaque) {
    super(position);
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

  public abstract void accept(TileVisitor visitor);
}
