package stardeath.participants;

import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public abstract class ParticipantAdapter implements ParticipantVisitor {

  @Override
  public void visitParticipant(Player player) {

  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {

  }

  @Override
  public void visitParticipant(Soldier soldier) {

  }

  @Override
  public void visitParticipant(Wookie wookie) {

  }
}
