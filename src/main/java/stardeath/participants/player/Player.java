package stardeath.participants.player;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;

public class Player extends Participant {

  public Player(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
