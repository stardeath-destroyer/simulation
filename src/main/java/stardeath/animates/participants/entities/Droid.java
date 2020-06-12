package stardeath.animates.participants.entities;

import stardeath.animates.participants.Participant;
import stardeath.animates.participants.Faction;

public abstract class Droid extends Participant {

  public Droid(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }
}
