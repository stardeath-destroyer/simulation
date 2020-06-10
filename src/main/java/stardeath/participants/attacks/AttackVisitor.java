package stardeath.participants.attacks;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;

public abstract class AttackVisitor implements ParticipantVisitor {

  public abstract <A extends Participant & Attacker> void visitAttacker(A participant);
  public abstract <S extends Participant & SplashAttacker> void visitSplashAttacker(S participant);

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    visitAttacker(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    visitAttacker(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    visitAttacker(wookie);
  }
}
