package stardeath;

import stardeath.rendering.OutputStreamRenderer;
import stardeath.world.Floor;
import stardeath.world.Tile;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.HorizontalWall;
import stardeath.world.tiles.VerticalWall;
import stardeath.world.tiles.Wall;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    Floor firstFloor = new Floor(
        new VerticalWall(0, 0),
        new VerticalWall(1, 0),
        new VerticalWall(2, 0),
        new VerticalWall(3, 0),
        new VerticalWall(4, 0),
        new Armory(1,1),
        new Dump(2,1),
        new Elevator(9,9),
        new HorizontalWall(0, 1),
        new HorizontalWall(0, 2),
        new HorizontalWall(0, 3),
        new HorizontalWall(0, 4)
    );
    Controller controller = new Controller(new OutputStreamRenderer(System.out), firstFloor);

    while (true) {
      Thread.sleep(1000);
      controller.step();
      controller.draw();
    }
  }
}
