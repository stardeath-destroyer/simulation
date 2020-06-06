package stardeath.participants;

import stardeath.Entity;
import stardeath.participants.factions.Faction;
import stardeath.participants.movements.Walker;

public abstract class Participant extends Entity implements Walker {

  private Faction memberOf;

  public abstract void accept(ParticipantVisitor visitor);
}
