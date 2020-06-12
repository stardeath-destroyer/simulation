package stardeath.animates.participants.entities;

import stardeath.animates.participants.Participant;
import stardeath.animates.participants.attacks.Attacker;
import stardeath.animates.participants.Faction;
import stardeath.world.Vector;

public abstract class Human extends Participant implements Attacker {

  public Human(Vector position, Faction faction, int hp) {
    super(position, faction, hp);
  }
}
