package stardeath.world;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import stardeath.Entity;
import stardeath.animates.Animate;
import stardeath.animates.participants.Participant;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.tiles.Terminal;
import stardeath.world.visibility.RayCasting;
import stardeath.world.visitors.DefaultTileVisitor;
import stardeath.world.visitors.TileVisitor;

public class World {

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

  public State getState() {
    return state;
  }

  public boolean moveUp() {
    if (current < floors.size() - 1) {
      current++;
      return true;
    } else {
      return false;
    }
  }

  public boolean moveDown() {
    if (current > 0) {
      current--;
      return true;
    } else {
      return false;
    }
  }

  public List<Floor> all() {
    return floors;
  }

  private Floor current() {
    return floors.get(current);
  }

  public final int getWidth() {
    return current().getWidth();
  }

  public final int getHeight() {
    return current().getHeight();
  }

  public void addAnimate(Animate animate) {
    current().addAnimate(animate);
  }

  public void spawn() {
    current().spawn();
  }

  /*
  public void destroyTerminal(Terminal terminal) {
    current().destroyTerminal(terminal);
    updateState();
  }
  */

  public void updateState() {
    final int[] onlineTerminals = {0};
    floors.forEach(f -> f.visitTiles(
        new DefaultTileVisitor(t -> {}) {
          @Override
          public void visitTile(Terminal terminal) {
            if (terminal.isOnline()) {
              ++onlineTerminals[0];
            }
          }
        }
    ));

    if (onlineTerminals[0] == 0) {
      state = State.DESTROYED;
    }
  }

  public Optional<Animate> participantAt(Vector position) {
    return current().participantAt(position);
  }

  public Optional<Tile> tileAt(Vector position) {
    return current().tileAt(position);
  }

  public void visitVisibleAnimatesFrom(Entity entity, int radius, AnimateVisitor visitor) {
    visitVisibleTilesFrom(entity, radius, new DefaultTileVisitor(t ->
            current().participantAt(t.getPosition())
                .ifPresent(p -> p.accept(visitor))
        )
    );
  }

  public void visitVisibleAnimatesFrom(Participant participant, AnimateVisitor visitor) {
    visitVisibleAnimatesFrom(participant, participant.getVisibilityRange(), visitor);
  }

  public void visitVisibleTilesFrom(Participant participant, TileVisitor visitor) {
    visitVisibleTilesFrom(participant, participant.getVisibilityRange(), visitor);
  }

  public void visitVisibleTilesFrom(Entity entity, int radius, TileVisitor visitor) {
    RayCasting.compute(entity, radius,
        (v) -> current().tileAt(v).map(t -> t.isOpaque() && (!entity.getPosition().equals(v))).orElse(false),
        (v) -> current().tileAt(v).ifPresent(t -> t.accept(visitor)));
  }

  public void visitAnimates(AnimateVisitor visitor) {
    current().visitAnimates(visitor);
  }

  public void visitTiles(TileVisitor visitor) {
    current().visitTiles(visitor);
  }

  public static class Builder {

    private Map<Integer, Floor> floors = new TreeMap<>();

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
