package stardeath.participants.entities;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.attacks.SplashAttacker;
import stardeath.participants.factions.Faction;

public class Wookie extends Participant implements SplashAttacker {

  public Wookie(int x, int y) {
    super(x, y, Faction.Rebels, 90);
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
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
