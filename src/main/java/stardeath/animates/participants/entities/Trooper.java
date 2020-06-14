package stardeath.animates.participants.entities;

import stardeath.animates.participants.Faction;
import stardeath.animates.participants.attacks.Attacker;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.Vector;

public class Trooper extends Soldier {

  protected Trooper(Vector position, int hp, int visibilityRange) {
    super(position, Faction.Empire, hp, visibilityRange);
  }

  public Trooper(Vector position) {
    super(position, Faction.Empire, 20, 5);
  }

  @Override
  public int getAttackRange() {
    return 3;
  }

  @Override
  public int getAttackDamage() {
    return 10;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
