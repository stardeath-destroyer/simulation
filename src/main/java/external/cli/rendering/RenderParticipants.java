package external.cli.rendering;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public class RenderParticipants implements ParticipantVisitor {

  private final char[][] buffer;

  public RenderParticipants(char[][] buffer) {
    this.buffer = buffer;
  }

  private void setGrid(Participant participant, char symbol) {
    buffer[participant.getY()][participant.getX()] = symbol;
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
