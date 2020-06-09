package stardeath.rendering;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import stardeath.participants.Participant;
import stardeath.world.Floor;
import stardeath.world.Tile;

public class OutputStreamRenderer implements Renderer {

  private final OutputStream stream;

  public OutputStreamRenderer(OutputStream stream) {
    this.stream = stream;
  }

  @Override
  public void render(Floor floor, Collection<Participant> participants) {
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
    RenderParticipants renderParticipants = new RenderParticipants(elements);

    floor.visit(renderFloor);
    participants.forEach(p -> p.accept(renderParticipants));

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
