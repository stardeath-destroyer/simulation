package stardeath.animates.participants;

import stardeath.animates.Animate;
import stardeath.animates.participants.movements.Walker;

public abstract class Participant extends Animate implements Walker {

  private Faction memberOf;
  private int hp;

  public Participant(int x, int y, Faction faction, int hp) {
    super(x, y);
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
