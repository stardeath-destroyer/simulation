package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.Faction;
import stardeath.world.Vector;

public class Wookie extends Participant {

  public Wookie(Vector position) {
    super(position, Faction.Rebels, 90, 6);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
