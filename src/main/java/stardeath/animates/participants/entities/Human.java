package stardeath.animates.participants.entities;

import stardeath.animates.participants.Participant;
import stardeath.animates.participants.attacks.Attacker;
import stardeath.animates.participants.Faction;

public abstract class Human extends Participant implements Attacker {

  public Human(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }
}
