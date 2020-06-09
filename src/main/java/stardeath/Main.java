package stardeath;

import java.io.IOException;
import java.io.InputStream;
import stardeath.rendering.lanterna.LanternaRenderer;
import stardeath.world.Floor;
import stardeath.world.Tile;
import stardeath.world.io.Decoding;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException, IOException {

    InputStream stream = Main.class
        .getClassLoader()
        .getResourceAsStream("test.floor");

    Floor firstFloor = new Floor(
        Decoding.readTiles(stream).stream().toArray(Tile[]::new)
    );
    Controller controller = new Controller(new LanternaRenderer(), firstFloor);

    while (true) {
      Thread.sleep(1000);
      controller.step();
      controller.draw();
    }
  }
}
