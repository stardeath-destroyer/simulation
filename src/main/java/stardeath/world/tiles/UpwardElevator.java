package stardeath.world.tiles;

import stardeath.world.visitors.TileVisitor;

public class UpwardElevator extends Elevator {

  public UpwardElevator(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
