package stardeath.participants.attacks;

import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;

public abstract class AttackVisitor implements ParticipantVisitor {

  public abstract void visitAttacker(Attacker attacker);

  public abstract void visitSplashAttacker(SplashAttacker attacker);

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
