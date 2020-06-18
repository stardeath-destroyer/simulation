package stardeath.animates.participants;

import stardeath.animates.Animate;
import stardeath.world.Vector;

/**
 * A participant is an Animate that is some kind of being. It can be a soldier, a trooper, a wookie
 * and so on. It's main attributes are that he has some health stats and is a member of either the
 * empire or the rebels
 */
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

  /**
   * Returns the remaining health points of the Participant
   * @return The remaining health points of the Participant
   */
  public int getHealthPoints() {
    return hp;
  }

  /**
   * Returns the visibility range of the participant
   * @return The visibility range of the participant
   */
  public int getVisibilityRange() {
    return this.visibilityRange;
  }

  /**
   * Damages the participant by a certain amount
   * @param amount The amount of damage the participant will receive
   */
  public final void damage(int amount) {
    hp -= amount;
  }

  /**
   * Gets the faction of the participant, either he is a member of The Empire or he is a Rebel
   * @return The faction of the participant
   */
  public final Faction getFaction() {
    return memberOf;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean shouldRemove() {
    return super.shouldRemove() || hp <= 0;
  }
}
