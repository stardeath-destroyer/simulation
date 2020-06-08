package stardeath.participants.entities;

import stardeath.participants.Participant;
import stardeath.participants.attacks.Attacker;

public abstract class Human extends Participant implements Attacker {

  public Human(int x, int y) {
    super(x, y);
  }
}
