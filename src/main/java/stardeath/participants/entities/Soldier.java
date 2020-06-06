package stardeath.participants.entities;

import stardeath.participants.ParticipantVisitor;

public class Soldier extends Human {

  @Override
  public int getX() {
    return 5;
  }

  @Override
  public int getY() {
    return 5;
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
