package stardeath.participants;

import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public interface ParticipantVisitor {

  void visitParticipant(Player player);

  void visitParticipant(JumpTrooper trooper);

  void visitParticipant(Soldier soldier);

  void visitParticipant(Wookie wookie);
}
