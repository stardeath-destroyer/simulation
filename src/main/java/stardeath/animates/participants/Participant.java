package stardeath.animates.participants;

import stardeath.animates.Animate;
import stardeath.world.Vector;

public abstract class Participant extends Animate {

  private final Faction memberOf;
  private int hp;
  private final int visibilityRange;

  public Participant(Vector position, Faction faction, int hp, int visibilityRange) {
    super(position);
    this.memberOf = faction;
    this.hp = hp;
    this.visibilityRange = visibilityRange;
  }

  public int getHealthPoints() {
    return hp;
  }

  public int getVisibilityRange() {
    return this.visibilityRange;
  }

  public final void damage(int amount) {
    hp -= amount;
  }

  public final Faction getFaction() {
    return memberOf;
  }

  @Override
  public boolean shouldRemove() {
    return super.shouldRemove() || hp <= 0;
  }
}
