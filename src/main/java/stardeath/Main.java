package stardeath;

import external.io.Decoding;
import external.lanterna.Lanterna;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.zip.ZipFile;
import stardeath.world.World;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws IOException, URISyntaxException {

    String level = "visibility.floor";

    if (args.length >= 1) {
      level = args[0];
    }

    World world = Decoding.loadWorld(new ZipFile("src/main/resources/world3.stardeath"));

    Controller controller = new Controller(new Lanterna(), world);

    while (true) {
      controller.step();
      controller.draw();
    }
  }
}
