package stardeath.animates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.Entity;
import stardeath.animates.actions.Action;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.World;

public abstract class Animate extends Entity {

  private final List<Action> actions = new ArrayList<>();
  private boolean visible;
  private boolean remove;

  protected Animate(Vector position) {
    super(position);
    this.visible = false;
    this.remove = false;
  }

  public boolean isVisible() {
    return visible;
  }

  public void remove() {
    this.remove = true;
  }

  public boolean shouldRemove() {
    return remove;
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

  public void setPosition(Vector position) {
    this.position = position;
  }

  public abstract void accept(AnimateVisitor visitor);

  public class MoveAction implements Action {

    private final Vector delta;

    public MoveAction(Vector delta) {
      this.delta = delta;
    }

    @Override
    public void execute(World world) {
      Vector newPosition = getPosition().add(delta);

      if (!world.tileAt(newPosition).map(Tile::isOpaque).orElse(false)) {
        Animate.this.setPosition(newPosition);
      }
    }
  }
}
