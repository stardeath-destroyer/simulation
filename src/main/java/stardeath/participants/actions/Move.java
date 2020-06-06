package stardeath.participants.actions;

import stardeath.participants.Participant;

public class Move implements Action {

  private final int deltaX;
  private final int deltaY;

  public Move(int x, int y) {
    this.deltaX = x;
    this.deltaY = y;
  }

  private static int clamp(int low, int value, int high) {
    return Math.max(low, Math.min(value, high));
  }

  @Override
  public void execute(Participant participant) {
    int prevX = participant.getX();
    int prevY = participant.getY();
    int nextX = clamp(0, prevX + deltaX, 9);
    int nextY = clamp(0, prevY + deltaY, 9);
    participant.setPosition(nextX, nextY);
  }
}
