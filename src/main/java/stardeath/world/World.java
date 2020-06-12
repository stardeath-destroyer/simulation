package stardeath.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {
  private final List<Floor> floors;

  private World(List<Floor> floors) {
    this.floors = Collections.unmodifiableList(floors);
  }

  public Floor current() {
    return floors.get(0);
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
