package stardeath.animates.participants.entities;

import stardeath.animates.participants.Faction;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.Vector;

/**
 * Basic trooper
 */
public class Trooper extends Soldier {

  protected Trooper(Vector position, int hp, int visibilityRange) {
    super(position, Faction.Empire, hp, visibilityRange);
  }

  public Trooper(Vector position) {
    super(position, Faction.Empire, 20, 5);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
