package external.cli.rendering;

import stardeath.animates.AnimateVisitor;
import stardeath.participants.Participant;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public class RenderParticipants implements AnimateVisitor {

  private final char[][] buffer;

  public RenderParticipants(char[][] buffer) {
    this.buffer = buffer;
  }

  private void setGrid(Participant participant, char symbol) {
    if (participant.isVisible()) {
      buffer[participant.getY()][participant.getX()] = symbol;
    }
  }

  @Override
  public void visitParticipant(Player player) {
    setGrid(player, 'P');
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    setGrid(trooper, 'T');
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    setGrid(soldier, 'S');
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    setGrid(wookie, 'W');
  }
}
