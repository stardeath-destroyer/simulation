package stardeath;

import external.lanterna.Lanterna;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import stardeath.world.Floor;
import external.io.Decoding;
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


    final Path path = Paths.get("src/main/resources/world.stardeath");
    final URI uri = URI.create("jar:" + path.toUri());



    World world = Decoding.loadWorld(uri);

    Controller controller = new Controller(new Lanterna(), world);

    while (true) {
      controller.step();
      controller.draw();
    }
  }
}
