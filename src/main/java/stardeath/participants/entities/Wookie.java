package stardeath.participants.entities;

import stardeath.EntityVisitor;
import stardeath.participants.Participant;
import stardeath.participants.attacks.SplashAttacker;

public class Wookie extends Participant implements SplashAttacker {

  @Override
  public void accept(EntityVisitor visitor) {
    visitor.visitEntity(this);
  }
}
