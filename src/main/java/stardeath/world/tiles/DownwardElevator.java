package stardeath.world.tiles;

import stardeath.world.visitors.TileVisitor;

public class DownwardElevator extends Elevator {

  public DownwardElevator(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
