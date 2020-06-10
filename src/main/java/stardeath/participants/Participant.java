package stardeath.participants;

import stardeath.Animate;
import stardeath.participants.factions.Faction;
import stardeath.participants.movements.Walker;

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

  public boolean isAlive() {
    return hp <= 0;
  }

  public abstract void accept(ParticipantVisitor visitor);

}
