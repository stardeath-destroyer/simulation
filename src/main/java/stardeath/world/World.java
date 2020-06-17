package stardeath.world;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import stardeath.Entity;
import stardeath.animates.Animate;
import stardeath.animates.participants.Participant;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.controller.visitors.GameStateVisitor;
import stardeath.world.visibility.RayCasting;
import stardeath.world.visitors.DefaultTileVisitor;
import stardeath.world.visitors.TileVisitor;

/**
 * A class representing the state of the game
 */
public class World {

  /**
   * Represents the different states in which the deathstar can be
   */
  public enum State {
    DESTROYED,
    SAVED,
    UNDER_ATTACK,
  }

  private final List<Floor> floors;
  private int current;
  private State state;

  private World(List<Floor> floors) {
    this.floors = floors;
    this.current = 0;
    state = State.UNDER_ATTACK;
  }

  /**
   * Gets the current state of the deathstar
   * @return The current state of the deathstar
   */
  public State getState() {
    return state;
  }

  /**
   * Moves up one floor
   * @return True if we moved up, false otherwise
   */
  public boolean moveUp() {
    if (current < floors.size() - 1) {
      current++;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Moves down one floor
   * @return True if we moved down, false otherwise
   */
  public boolean moveDown() {
    if (current > 0) {
      current--;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns all the floors that compose the deathstar
   * @return A list of all floors
   */
  public List<Floor> all() {
    return floors;
  }

  private Floor current() {
    return floors.get(current);
  }

  /**
   * Get the width of the current floor
   * @return The width of the current floor
   */
  public final int getWidth() {
    return current().getWidth();
  }

  /**
   * Get the height of the current floor
   * @return The height of the current floor
   */
  public final int getHeight() {
    return current().getHeight();
  }

  /**
   * Adds an animate on the current floor
   * @param animate The animate to add
   */
  public void addAnimate(Animate animate) {
    current().addAnimate(animate);
  }

  /**
   * Updates the animates that are on the floor
   */
  public void spawn() {
    current().spawn();
  }

  /**
   * Updates the state of the game
   */
  public void updateState() {
    final GameStateVisitor stateVisitor = new GameStateVisitor();
    floors.forEach(f -> f.visitTiles(stateVisitor));
    floors.forEach(f -> f.visitAnimates(stateVisitor));

    // Bias towards player, i.e. we accept suicide missions
    if (stateVisitor.onlineTerminals() == 0) {
      state = State.DESTROYED;
    } else if (stateVisitor.isPlayerDead()) {
      state = State.SAVED;
    }
  }

  /**
   * Visits an animate at a given position. If no animate is found at the given position, nothing is
   * done.
   * @param position The position to visit
   * @param visitor The visitor to visit the animate with
   */
  public void visitAnimateAt(Vector position, AnimateVisitor visitor) {
    current().participantAt(position).ifPresent(p -> p.accept(visitor));
  }

  /**
   * Visits a tile at a given position. If no tile is found at the given position, nothing is
   * done.
   * @param position The position to visit
   * @param visitor The visitor to visit the tile with
   */
  public void visitTileAt(Vector position, TileVisitor visitor) {
    current().tileAt(position).ifPresent(t -> t.accept(visitor));
  }

  /**
   * Uses raycasting to visit only visible animates from a specific position, with a certain radius
   * @param entity The entity from which the floor is seen
   * @param radius The distance at which the entity can see
   * @param visitor The visitor to visit the animates with
   */
  public void visitVisibleAnimatesFrom(Entity entity, int radius, AnimateVisitor visitor) {
    visitVisibleTilesFrom(entity, radius, new DefaultTileVisitor(t ->
            current().participantAt(t.getPosition())
                .ifPresent(p -> p.accept(visitor))
        )
    );
  }

  /**
   * Uses raycasting to visit only visible animates from a specific position, with a certain radius
   * @param participant The participant from which the floor is seen
   * @param visitor The visitor to visit the animates with
   */
  public void visitVisibleAnimatesFrom(Participant participant, AnimateVisitor visitor) {
    visitVisibleAnimatesFrom(participant, participant.getVisibilityRange(), visitor);
  }

  /**
   * Uses raycasting to visit only visible tiles from a specific position, with a certain radius
   * @param participant The participant from which the floor is seen
   * @param visitor The visitor to visit the tiles with
   */
  public void visitVisibleTilesFrom(Participant participant, TileVisitor visitor) {
    visitVisibleTilesFrom(participant, participant.getVisibilityRange(), visitor);
  }

  /**
   * Uses raycasting to visit only visible tiles from a specific position, with a certain radius
   * @param entity The entity from which the floor is seen
   * @param radius The distance at which the entity can see
   * @param visitor The visitor to visit the tiles with
   */
  public void visitVisibleTilesFrom(Entity entity, int radius, TileVisitor visitor) {
    RayCasting.compute(entity, radius,
        (v) -> current().tileAt(v).map(t -> t.isOpaque() && (!entity.getPosition().equals(v))).orElse(false),
        (v) -> current().tileAt(v).ifPresent(t -> t.accept(visitor)));
  }

  /**
   * Visits all the animates on the current floor
   * @param visitor The visitor to visit the animates with
   */
  public void visitAnimates(AnimateVisitor visitor) {
    current().visitAnimates(visitor);
  }

  /**
   * Visits all tiles on the current floor
   * @param visitor The visitor to visit the tiles with
   */
  public void visitTiles(TileVisitor visitor) {
    current().visitTiles(visitor);
  }

  public static class Builder {

    private final Map<Integer, Floor> floors = new TreeMap<>();

    public void addFloor(int index, Floor floor) {
      floors.put(index, floor);
    }

    public World build() {
      return new World(floors.entrySet().stream()
          .sorted(Entry.comparingByKey())
          .map(Entry::getValue)
          .collect(Collectors.toList()));
    }
  }
}
