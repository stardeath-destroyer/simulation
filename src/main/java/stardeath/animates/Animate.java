package stardeath.animates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.Entity;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.Vector;
import stardeath.world.World;
import stardeath.world.visitors.DefaultTileVisitor;

/**
 * An animate is an Entity that can move on the map
 */
public abstract class Animate extends Entity {

  private final List<Action> actions = new ArrayList<>();
  private boolean visible;
  private boolean remove;

  protected Animate(Vector position) {
    super(position);
    this.visible = false;
    this.remove = false;
  }

  /**
   * Is the visible ?
   * @return True if the animate is visible
   */
  public boolean isVisible() {
    return visible;
  }

  /**
   * Removes the animate from the game
   */
  public void remove() {
    this.remove = true;
  }

  /**
   * Returns true if we should remove the animate from the game
   * @return True if we should remove the animate from the game
   */
  public boolean shouldRemove() {
    return remove;
  }

  /**
   * Hides the animate. Removes all its visibility
   */
  public void hide() {
    this.visible = false;
  }

  /**
   * Shows the animate. Add some visibility
   */
  public void show() {
    this.visible = true;
  }

  /**
   * Add an action to do to the animate
   * @param action Action to add
   */
  public final void addAction(Action action) {
    actions.add(action);
  }

  /**
   * Remove all stored actions to do
   */
  public final void clearActions() {
    actions.clear();
  }

  /**
   * Get an unmodifiable list of actions that the animate must do
   * @return An unmodifiable list of actions that the animate must do
   */
  public final List<Action> getActions() {
    return Collections.unmodifiableList(actions);
  }

  /**
   * Sets the position of the animate
   * @param position The new position of the animate
   */
  public void setPosition(Vector position) {
    this.position = position;
  }

  /**
   * Accepts an AnimateVisitor
   * @param visitor The visitor to accept
   */
  public abstract void accept(AnimateVisitor visitor);

  /**
   * Represents a movement that an animate can do
   */
  public class MoveAction implements Action {

    private final Vector delta;

    public MoveAction(Vector delta) {
      this.delta = delta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(World world) {
      Vector newPosition = getPosition().add(delta);

      world.visitTileAt(newPosition, new DefaultTileVisitor(tile -> {
        if (!tile.isOpaque()) {
          Animate.this.setPosition(newPosition);
        }
      }));
    }
  }
}
