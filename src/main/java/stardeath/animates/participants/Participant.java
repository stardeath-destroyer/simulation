package stardeath.animates.participants;

import stardeath.animates.Animate;
import stardeath.animates.participants.movements.Walker;
import stardeath.world.Vector;

public abstract class Participant extends Animate implements Walker {

  private Faction memberOf;
  private int hp;

  public Participant(Vector position, Faction faction, int hp) {
    super(position);
    this.memberOf = faction;
    this.hp = hp;
  }

  public final void damage(int amount) {
    hp -= amount;
  }

  @Override
  public boolean shouldRemove() {
    return super.shouldRemove() || hp < 0;
  }
}
