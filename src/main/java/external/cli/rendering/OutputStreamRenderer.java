package external.cli.rendering;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import stardeath.controller.interactions.Renderer;
import stardeath.world.Floor;
import stardeath.world.Tile;

public class OutputStreamRenderer implements Renderer {

  private final OutputStream stream;

  public OutputStreamRenderer(OutputStream stream) {
    this.stream = stream;
  }

  @Override
  public void render(Floor floor) {
    int maxX = 0, maxY = 0;
    for (Tile tile : floor.getTiles()) {
      maxX = Math.max(maxX, tile.getX() + 1);
      maxY = Math.max(maxY, tile.getY() + 1);
    }

    char[][] elements = new char[maxY][maxX];

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
