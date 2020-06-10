package stardeath.participants.entities;

import stardeath.animates.AnimateVisitor;
import stardeath.participants.factions.Faction;

public class Soldier extends Human {

  public Soldier(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

  @Override
  public int getAttackRange() {
    return 4;
  }

  @Override
  public int getAttackDamage() {
    return 150;
  }
}
