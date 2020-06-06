package stardeath.participants;

import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;

public interface ParticipantVisitor {

  void visitParticipant(Soldier soldier);

  void visitParticipant(Wookie wookie);
}
