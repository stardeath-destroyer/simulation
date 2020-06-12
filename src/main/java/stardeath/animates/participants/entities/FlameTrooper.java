package stardeath.animates.participants.entities;

import stardeath.animates.participants.attacks.SplashAttacker;
import stardeath.world.Vector;

public class FlameTrooper extends Trooper implements SplashAttacker {

  public FlameTrooper(Vector position) {
    super(position, 45);
  }
}
