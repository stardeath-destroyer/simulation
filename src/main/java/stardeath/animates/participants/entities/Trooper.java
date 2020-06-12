package stardeath.animates.participants.entities;

import stardeath.animates.participants.Faction;
import stardeath.world.Vector;

public class Trooper extends Soldier {

  protected Trooper(Vector position, int hp) {
    super(position, Faction.Empire, hp);
  }

  public Trooper(Vector position) {
    super(position, Faction.Empire, 20);
  }
}
