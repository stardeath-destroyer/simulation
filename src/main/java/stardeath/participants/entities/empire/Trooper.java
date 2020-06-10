package stardeath.participants.entities.empire;

import stardeath.participants.entities.Soldier;
import stardeath.participants.factions.Faction;

public class Trooper extends Soldier {

  protected Trooper(int x, int y, int hp) {
    super(x, y, Faction.Empire, hp);
  }

  public Trooper(int x, int y) {
    super(x, y, Faction.Empire, 20);
  }
}
