package external.cli.rendering;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import stardeath.controller.interactions.Renderer;
import stardeath.world.Floor;

public class OutputStreamRenderer implements Renderer {

  private final OutputStream stream;

  public OutputStreamRenderer(OutputStream stream) {
    this.stream = stream;
  }

  @Override
  public void render(Floor floor) {
    char[][] elements = new char[floor.getHeight()][floor.getWidth()];

    for (char[] element : elements) {
      Arrays.fill(element, ' ');
    }

    RenderFloor renderFloor = new RenderFloor(elements);
    RenderAnimates renderAnimates = new RenderAnimates(elements);

    floor.visitTiles(renderFloor);
    floor.visitAnimates(renderAnimates);

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
