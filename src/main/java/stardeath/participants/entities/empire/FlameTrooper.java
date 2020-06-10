package stardeath.participants.entities.empire;

import stardeath.participants.attacks.SplashAttacker;

public class FlameTrooper extends Trooper implements SplashAttacker {

  public FlameTrooper(int x, int y) {
    super(x, y, 45);
  }
}
