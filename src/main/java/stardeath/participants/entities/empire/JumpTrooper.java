package stardeath.participants.entities.empire;

import stardeath.animates.AnimateVisitor;
import stardeath.animates.movements.Jumper;

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
