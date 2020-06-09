package external.lanterna.rendering;

import com.googlecode.lanterna.TextCharacter;
import java.util.Optional;
import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public class RenderParticipants implements ParticipantVisitor {

  private final TextCharacter[][] buffer;
  private Player player;

  public RenderParticipants(TextCharacter[][] buffer) {
    this.buffer = buffer;
  }

  public Optional<Player> getPlayer() {
    return Optional.ofNullable(player);
  }

  private void setGrid(Participant participant, TextCharacter c) {
    buffer[participant.getX()][participant.getY()] = c;
  }

  @Override
  public void visitParticipant(Player player) {
    setGrid(player, new TextCharacter('P'));
    this.player = player;
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    setGrid(trooper, new TextCharacter('T'));
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    setGrid(soldier, new TextCharacter('S'));
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    setGrid(wookie, new TextCharacter('W'));
  }
}
