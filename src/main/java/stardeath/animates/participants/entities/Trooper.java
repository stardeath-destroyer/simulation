package stardeath.animates.participants.entities;

import stardeath.animates.participants.Faction;
import stardeath.world.Vector;

public class Trooper extends Soldier {

  protected Trooper(Vector position, int hp, int visibilityRange) {
    super(position, Faction.Empire, hp, visibilityRange);
  }

  public Trooper(Vector position) {
    super(position, Faction.Empire, 20, 5);
  }
}
