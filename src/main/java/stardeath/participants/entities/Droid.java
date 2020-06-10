package stardeath.participants.entities;

import stardeath.participants.Participant;
import stardeath.participants.factions.Faction;

public abstract class Droid extends Participant {

  public Droid(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }
}
