package stardeath.participants.player;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;

public class Player extends Participant {

  private static final int DEFAULT_VISIBILITY_RANGE = 8;

  private int visibilityRange;

  public Player(int x, int y) {
    super(x, y);
    this.visibilityRange = DEFAULT_VISIBILITY_RANGE;
  }

  public int getVisibilityRange() {
    return this.visibilityRange;
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
