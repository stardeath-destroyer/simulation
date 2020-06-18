package stardeath.animates.participants.entities;

import stardeath.animates.participants.Participant;
import stardeath.animates.participants.Faction;
import stardeath.world.Vector;

/**
 * A human
 */
public abstract class Human extends Participant {

  public Human(Vector position, Faction faction, int hp, int visibilityRange) {
    super(position, faction, hp, visibilityRange);
  }
}
