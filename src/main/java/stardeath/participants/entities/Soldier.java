package stardeath.participants.entities;

import stardeath.EntityVisitor;

public class Soldier extends Human {

  @Override
  public void accept(EntityVisitor visitor) {
    visitor.visitEntity(this);
  }
}
