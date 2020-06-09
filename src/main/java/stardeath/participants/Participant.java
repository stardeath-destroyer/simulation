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
    private Floor floor;

    public MoveAction(int x, int y, Floor floor) {
      this.deltaX = x;
      this.deltaY = y;
      this.floor = floor;
    }

    @Override
    public void execute() {
      int newX = getX() + deltaX;
      int newY = getY() + deltaY;

      if (floor.getTiles().stream()
          .filter(tile -> tile.getX() == newX && tile.getY() == newY)
          .noneMatch(Tile::isOpaque)) {
        Participant.this.setPosition(getX() + deltaX, getY() + deltaY);
      }
    }
  }
}
