package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.Vector;

public class JumpTrooper extends Trooper {

  public JumpTrooper(Vector position) {
    super(position, 45, 10);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
