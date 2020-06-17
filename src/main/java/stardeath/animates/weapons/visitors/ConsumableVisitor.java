package stardeath.animates.weapons.visitors;

import stardeath.visitors.NoOpEntityVisitor;

/**
 * Represents all visitors that can be consumed (such as grenades or blasters)
 */
public class ConsumableVisitor extends NoOpEntityVisitor {

  protected boolean consumed = false;

  /**
   * Returns true if the visitor is consumed
   * @return True if the visitor is consumed
   */
  public boolean isConsumed() {
    return consumed;
  }
}
