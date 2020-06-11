package stardeath.animates.participants.entities;

import stardeath.animates.participants.Faction;

public class Trooper extends Soldier {

  protected Trooper(int x, int y, int hp) {
    super(x, y, Faction.Empire, hp);
  }

  public Trooper(int x, int y) {
    super(x, y, Faction.Empire, 20);
  }
}
