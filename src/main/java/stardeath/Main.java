package stardeath;

import stardeath.rendering.OutputStreamRenderer;
import stardeath.world.Floor;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {

    Floor firstFloor = new Floor();
    Controller controller = new Controller(new OutputStreamRenderer(System.out), firstFloor);

    while (true) {
      Thread.sleep(1000);
      controller.step();
      controller.draw();
    }
  }
}
