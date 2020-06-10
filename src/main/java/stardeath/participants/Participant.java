package stardeath.participants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.Entity;
import stardeath.participants.actions.Action;
import stardeath.participants.factions.Faction;
import stardeath.participants.movements.Walker;
import stardeath.world.Floor;
import stardeath.world.Tile;

public abstract class Participant extends Entity implements Walker {

  private final List<Action> actions = new ArrayList<>();
  private Faction memberOf;

  public Participant(int x, int y) {
    super(x, y);
  }

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

  public class MoveAction implements Action {

    private final int deltaX;
    private final int deltaY;

    public MoveAction(int x, int y) {
      this.deltaX = x;
      this.deltaY = y;
    }

    @Override
    public void execute(Floor level) {
      int newX = getX() + deltaX;
      int newY = getY() + deltaY;

      if (level.getTiles().stream()
          .filter(tile -> tile.getX() == newX && tile.getY() == newY)
          .noneMatch(Tile::isOpaque)) {
        Participant.this.setPosition(getX() + deltaX, getY() + deltaY);
      }
    }
  }
}
