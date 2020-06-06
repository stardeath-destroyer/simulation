package stardeath;

import java.util.ArrayList;
import java.util.List;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.FlameTrooper;
import stardeath.rendering.Renderer;
import stardeath.world.tiles.Wall;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) {
    List<Entity> entities = new ArrayList<>();

    entities.add(new Wookie());
    entities.add(new Wall());
    entities.add(new FlameTrooper());

    Renderer renderer = new Renderer();

    for (Entity entity : entities) {
      entity.accept(renderer);
    }

    System.out.println(renderer.toString());

    System.out.println("Hello world.");
  }
}
