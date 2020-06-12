package stardeath.world;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import stardeath.Entity;
import stardeath.world.visibility.RayCasting;
import stardeath.world.visitors.TileVisitor;

public class World {

  private final List<Floor> floors;
  private int current;

  private World(List<Floor> floors) {
    this.floors = floors;
    this.current = 0;
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

  public Floor current() {
    return floors.get(current);
  }

  public void visitVisibleTilesFrom(Entity entity, int radius, TileVisitor visitor) {
    RayCasting.compute(entity, radius,
        (v) -> current().tileAt(v).map(Tile::isOpaque).orElse(false),
        (v) -> current().tileAt(v).ifPresent(t -> t.accept(visitor)));
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
