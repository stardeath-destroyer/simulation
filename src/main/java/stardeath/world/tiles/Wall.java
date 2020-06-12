package stardeath.world.tiles;

import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class Wall extends Tile {

  public Wall(Vector position) {
    super(position, true);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
