package stardeath;

import external.io.Decoding;
import external.lanterna.Lanterna;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.zip.ZipFile;
import stardeath.controller.Controller;
import stardeath.world.World;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws IOException {
    World world = Decoding.loadWorld(new ZipFile("src/main/resources/world3.stardeath"));

    Controller controller = new Controller(new Lanterna(), world);

    while (true) {
      controller.step();
      controller.draw();
    }
  }
}
