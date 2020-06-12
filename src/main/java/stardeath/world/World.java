package stardeath.world;

import java.util.ArrayList;
import java.util.List;

public class World {
  private List<Floor> floors = new ArrayList<>();

  public Floor current() { return floors.get(0); }

  public void addFloor(Floor newFloor){
    floors.add(newFloor);
  }

  public static class Builder {
    public World build() {
      return new World();
    }
  }
}
