package stardeath.animates.participants.attacks;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;

public abstract class AttackVisitor implements AnimateVisitor {

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
