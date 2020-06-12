package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.attacks.SplashAttacker;
import stardeath.animates.participants.Faction;

public class Wookie extends Participant implements SplashAttacker {

  public Wookie(int x, int y) {
    super(x, y, Faction.Rebels, 90);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

  @Override
  public int getAttackRange() {
    return 0;
  }

  @Override
  public int getAttackDamage() {
    return 0;
  }
}