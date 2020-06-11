package stardeath.animates.participants.entities;

import stardeath.animates.participants.Faction;

public abstract class ForceUser extends Human {

  protected ForceUser(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }
}
