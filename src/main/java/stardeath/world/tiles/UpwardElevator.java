package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

public class UpwardElevator extends Elevator {

  public UpwardElevator(Vector vector) {
    super(vector);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
