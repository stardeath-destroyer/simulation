package stardeath.participants.player;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;

public class Player extends Participant {

  private int visibilityRange;

  public Player(int x, int y) {
    super(x, y);
    this.visibilityRange = 5;
  }

  public int getVisibilityRange() {
    return this.visibilityRange;
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
