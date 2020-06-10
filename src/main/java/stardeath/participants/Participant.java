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
  private int hp;
  private boolean visible;

  public Participant(int x, int y, Faction faction, int hp) {
    super(x, y);
    this.memberOf = faction;
    this.hp = hp;
    this.visible = false;
  }

  public final void damage(int amount) {
    hp -= amount;
  }

  public boolean isAlive() {
    return hp <= 0;
  }

  public boolean isVisible() {
    return visible;
  }

  public void hide() {
    this.visible = false;
  }

  public void show() {
    this.visible = true;
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
