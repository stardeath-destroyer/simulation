package external.cli.rendering;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import stardeath.controller.interactions.Renderer;
import stardeath.world.World;

/**
 * An implementation of a {@link Renderer} that simply outputs the current state to an existing
 * {@link OutputStream}, without trying to make it pretty.
 */
public class OutputStreamRenderer implements Renderer {

  private final OutputStream stream;

  public OutputStreamRenderer(OutputStream stream) {
    this.stream = stream;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(World world) {

    switch (world.getState()) {
      case SAVED:
        renderLost();
        break;
      case DESTROYED:
        renderWon();
        break;
      case UNDER_ATTACK:
        renderWorld(world);
        break;
    }
  }

  /**
   * Renders the winning state.
   */
  private void renderWon() {
    PrintWriter writer = new PrintWriter(stream);
    writer.println("YOU WON ! CONGRATS !");
    writer.flush();
  }

  /**
   * Renders the lost state.
   */
  private void renderLost() {
    PrintWriter writer = new PrintWriter(stream);
    writer.println("YOU LOST !");
    writer.flush();
  }

  /**
   * Renders the active state.
   *
   * @param world The {@link World} that we are displaying and rendering.
   */
  public void renderWorld(World world) {
    char[][] elements = new char[world.getHeight()][world.getWidth()];

    for (char[] element : elements) {
      Arrays.fill(element, ' ');
    }

    RenderFloor renderFloor = new RenderFloor(elements);
    RenderAnimates renderAnimates = new RenderAnimates(elements);

    world.visitTiles(renderFloor);
    world.visitAnimates(renderAnimates);

    try {
      for (char[] line : elements) {
        for (char tile : line) {
          stream.write(tile);
        }
        stream.write('\n');
      }
    } catch (IOException any) {
      // Too bad.
    }
  }
}
