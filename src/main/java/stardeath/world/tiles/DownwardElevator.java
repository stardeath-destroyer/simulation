package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class DownwardElevator extends Elevator {

  public DownwardElevator(Vector vector) {
    super(vector);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
