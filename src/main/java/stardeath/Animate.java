package stardeath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.participants.actions.Action;
import stardeath.world.Floor;
import stardeath.world.Tile;

public abstract class Animate extends Entity {

  private final List<Action> actions = new ArrayList<>();
  private boolean visible;

  protected Animate(int x, int y) {
    super(x, y);
    this.visible = false;
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

  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

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
        Animate.this.setPosition(getX() + deltaX, getY() + deltaY);
      }
    }
  }
}
