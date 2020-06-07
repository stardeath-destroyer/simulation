package stardeath.participants.entities.empire;

import stardeath.participants.ParticipantVisitor;
import stardeath.participants.movements.Jumper;

public class JumpTrooper extends Trooper implements Jumper {

  @Override
  public int getRange() {
    return 15;
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
