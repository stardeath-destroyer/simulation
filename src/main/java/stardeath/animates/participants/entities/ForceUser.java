package stardeath.animates.participants.entities;

import stardeath.animates.participants.Faction;
import stardeath.world.Vector;

public abstract class ForceUser extends Human {

  protected ForceUser(Vector position, Faction faction, int hp, int visibilityRange) {
    super(position, faction, hp, visibilityRange);
  }
}
