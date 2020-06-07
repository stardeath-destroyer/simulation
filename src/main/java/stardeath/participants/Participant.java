package stardeath.participants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.Entity;
import stardeath.participants.actions.Action;
import stardeath.participants.factions.Faction;
import stardeath.participants.movements.Walker;

public abstract class Participant extends Entity implements Walker {

  private final List<Action> actions = new ArrayList<>();
  private Faction memberOf;

  public final void addAction(Action action) {
    actions.add(action);
  }

  public final void clearActions() {
    actions.clear();
  }

  public final List<Action> getActions() {
    return Collections.unmodifiableList(actions);
  }

  public abstract void accept(ParticipantVisitor visitor);
}
