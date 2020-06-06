package stardeath.participants.entities;

import stardeath.EntityVisitor;

public class Soldier extends Human {

  @Override
  public int getX() {
    return 5;
  }

  @Override
  public int getY() {
    return 5;
  }

  @Override
  public void accept(EntityVisitor visitor) {
    visitor.visitEntity(this);
  }
}
