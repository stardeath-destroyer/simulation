package stardeath;

import external.cli.CLI;
import external.io.Decoding;
import external.lanterna.Lanterna;
import java.io.IOException;
import java.util.zip.ZipFile;
import stardeath.controller.Controller;
import stardeath.controller.InteractionsFactory;

/**
 * The main entry point of our application.
 */
public class Main {

  /**
   * Returns the {@link InteractionsFactory} that will be used to interact with the app.
   *
   * @param args The launch arguments.
   * @return The retrieved {@link InteractionsFactory}
   * @throws IOException If something went badly when creating the {@link InteractionsFactory}.
   */
  private static InteractionsFactory interactions(String[] args) throws IOException {
    if (args.length == 1 && args[0].equals("--simple")) {
      return new CLI();
    }
    return new Lanterna();
  }

  public static void main(String[] args) throws IOException {

    Controller controller = new Controller(
        interactions(args),
        Decoding.loadWorld(new ZipFile("src/main/resources/world.stardeath")
        ));

    while (true) {
      controller.step();
      controller.draw();
    }
  }
}
