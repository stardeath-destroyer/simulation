package stardeath.participants.movements;

import stardeath.participants.actions.Action;

public interface Jumper extends Walker {

  @Override
  default Action accept(MoveableVisitor visitor) {
    return Action.of(
        visitor.visitWalker(this),
        visitor.visitJumper(this)
    );
  }
}
