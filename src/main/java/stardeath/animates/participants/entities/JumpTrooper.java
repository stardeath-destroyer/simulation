package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.movements.Jumper;
import stardeath.world.Vector;

public class JumpTrooper extends Trooper implements Jumper {

  public JumpTrooper(Vector position) {
    super(position, 45, 10);
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
