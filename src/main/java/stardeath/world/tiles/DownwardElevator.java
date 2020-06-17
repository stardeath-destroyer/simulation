package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

/**
 * Sort of elevator that can only go downwards
 */
public class DownwardElevator extends Elevator {

  public DownwardElevator(Vector vector) {
    super(vector);
  }

  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
