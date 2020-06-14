package stardeath.animates.weapons.visitors;

import stardeath.world.visitors.NoOpEntityVisitor;

public class ConsumableVisitor extends NoOpEntityVisitor {

  protected boolean consumed = false;

  public boolean isConsumed() {
    return consumed;
  }
}
