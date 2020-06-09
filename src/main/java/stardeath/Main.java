package stardeath;

import external.lanterna.Lanterna;
import java.io.IOException;
import java.io.InputStream;
import stardeath.world.Floor;
import stardeath.world.Tile;
import external.io.Decoding;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws IOException {

    InputStream stream = Main.class
        .getClassLoader()
        .getResourceAsStream("test.floor");

    Floor firstFloor = new Floor(
        Decoding.readTiles(stream).stream().toArray(Tile[]::new)
    );
    Controller controller = new Controller(new Lanterna(), firstFloor);

    while (true) {
      controller.step();
      controller.draw();
    }
  }
}
