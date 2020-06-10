package stardeath.participants.entities;

import stardeath.animates.AnimateVisitor;
import stardeath.participants.factions.Faction;

public class Soldier extends Human {

  protected Soldier(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }

  public Soldier(int x, int y) {
    super(x, y, Faction.Rebels, 70);
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
