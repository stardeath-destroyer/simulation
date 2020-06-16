package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.Vector;

public class FlameTrooper extends Trooper {

  public FlameTrooper(Vector position) {
    super(position, 45, 7);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

}
