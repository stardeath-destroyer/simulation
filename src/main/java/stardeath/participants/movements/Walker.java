package stardeath.participants.movements;

import stardeath.participants.actions.Action;

public interface Walker {

  default Action accept(MoveableVisitor visitor) {
    return visitor.visitWalker(this);
  }
}
