package stardeath.world.tiles;

import stardeath.world.Vector;
import stardeath.world.visitors.TileVisitor;

/**
 * This kind of elevator will only go upwards
 */
public class UpwardElevator extends Elevator {

  public UpwardElevator(Vector vector) {
    super(vector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(TileVisitor visitor) {
    visitor.visitTile(this);
  }
}
