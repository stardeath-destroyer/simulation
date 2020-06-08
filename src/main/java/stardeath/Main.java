package stardeath;

import stardeath.rendering.OutputStreamRenderer;
import stardeath.world.Floor;
import stardeath.world.Tile;
import stardeath.world.tiles.Wall;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    Floor firstFloor = new Floor(
        new Wall(0, 0),
        new Wall(1, 0),
        new Wall(2, 0)
    );
    Controller controller = new Controller(new OutputStreamRenderer(System.out), firstFloor);

    while (true) {
      Thread.sleep(1000);
      controller.step();
      controller.draw();
    }
  }
}
