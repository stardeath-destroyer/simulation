package stardeath.rendering.lanterna;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public class RenderParticipants implements ParticipantVisitor {

  private final Screen screen;

  public RenderParticipants(Screen screen) {
    this.screen = screen;
  }

  private void setGrid(Participant participant, char symbol) {
    screen.setCharacter(participant.getX(), participant.getY(), new TextCharacter(symbol));
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
