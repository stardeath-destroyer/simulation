package stardeath.participants.actions;

import stardeath.participants.Participant;

public class Move implements Action {

  private final int deltaX;
  private final int deltaY;

  public Move(int x, int y) {
    this.deltaX = x;
    this.deltaY = y;
  }

  @Override
  public void execute(Participant participant) {
    participant.setPosition(participant.getX() + deltaX, participant.getY() + deltaY);
  }
}
