package stardeath.participants.entities.empire;

import stardeath.participants.entities.Soldier;
import stardeath.participants.factions.Faction;

public class Trooper extends Soldier {

  public Trooper(int x, int y, int hp) {
    super(x, y, Faction.Empire, hp);
  }
}
