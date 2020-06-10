package stardeath.world;

import java.util.List;

public class World {
  private List<Floor> floors;

  public Floor getFirst(){
    return floors.get(0);
  }

  public void addFloor(Floor newFloor){
    floors.add(newFloor);
  }
}
