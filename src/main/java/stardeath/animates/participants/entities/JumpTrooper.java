package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.movements.Jumper;

public class JumpTrooper extends Trooper implements Jumper {

  public JumpTrooper(int x, int y) {
    super(x, y, 45);
  }

  @Override
  public int getRange() {
    return 15;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
