package stardeath.participants.entities;

import stardeath.participants.factions.Faction;

public abstract class ForceUser extends Human {

  protected ForceUser(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }
}
