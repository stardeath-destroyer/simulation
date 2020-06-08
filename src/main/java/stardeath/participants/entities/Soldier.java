package stardeath.participants.entities;

import stardeath.participants.ParticipantVisitor;

public class Soldier extends Human {

  public Soldier(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
