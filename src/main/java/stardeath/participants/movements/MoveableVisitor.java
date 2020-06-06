package stardeath.participants.movements;

import stardeath.participants.actions.Action;

public interface MoveableVisitor {

  Action visitJumper(Jumper jumper);

  Action visitWalker(Walker walker);
}
