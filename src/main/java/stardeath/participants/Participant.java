package stardeath.participants;

import stardeath.Entity;
import stardeath.participants.factions.Faction;
import stardeath.participants.movements.Walker;

public abstract class Participant implements Entity, Walker {

  private Faction memberOf;
}
