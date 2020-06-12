package stardeath.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.Entity;
import stardeath.world.visibility.RayCasting;
import stardeath.world.visitors.TileVisitor;

public class World {
  private final List<Floor> floors;

  private World(List<Floor> floors) {
    this.floors = Collections.unmodifiableList(floors);
  }

  public Floor current() {
    return floors.get(0);
  }

  public void visitVisibleTilesFrom(Entity entity, int radius, TileVisitor visitor) {
    RayCasting.compute(entity, radius,
        (x, y) -> current().tileAt(x, y).isOpaque(),
        (x, y) -> current().tileAt(x, y).accept(visitor));
  }

  public static class Builder {
    private List<Floor> floors = new ArrayList<>();

    public void addFloor(Floor floor) {
      floors.add(floor);
    }

    public World build() {
      return new World(floors);
    }
  }
}
