package stardeath.participants;

import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;

public interface ParticipantVisitor {

  void visitParticipant(JumpTrooper trooper);

  void visitParticipant(Soldier soldier);

  void visitParticipant(Wookie wookie);
}
