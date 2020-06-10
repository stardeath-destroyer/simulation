package stardeath.participants.entities;

import stardeath.participants.Participant;
import stardeath.participants.attacks.Attacker;
import stardeath.participants.factions.Faction;

public abstract class Human extends Participant implements Attacker {

  public Human(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }
}
