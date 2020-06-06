package stardeath.participants.entities;

import stardeath.participants.ParticipantVisitor;

public class Soldier extends Human {

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
