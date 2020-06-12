package stardeath.animates.participants.entities;

import stardeath.animates.participants.Participant;
import stardeath.animates.participants.Faction;
import stardeath.world.Vector;

public abstract class Droid extends Participant {

  public Droid(Vector position, Faction faction, int hp) {
    super(position, faction, hp);
  }
}
