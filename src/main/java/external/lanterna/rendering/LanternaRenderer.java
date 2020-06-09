package external.lanterna.rendering;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.Collection;
import stardeath.interactions.Renderer;
import stardeath.participants.Participant;
import stardeath.world.Floor;

public class LanternaRenderer implements Renderer {

  private final Screen screen;

  public LanternaRenderer(Screen screen) throws IOException {
    this.screen = screen;
    this.screen.startScreen();
  }

  @Override
  public void render(Floor floor, Collection<Participant> players) {

    TextCharacter[][] buffer = new TextCharacter[floor.getWidth() + 1][floor.getHeight() + 1];

    RenderFloor renderFloor = new RenderFloor(buffer);
    RenderParticipants renderParticipants = new RenderParticipants(buffer);

    floor.visit(renderFloor);
    players.forEach(p -> p.accept(renderParticipants));

    try {
      for (int i = 0; i < buffer.length; i++) {
        for (int j = 0; j < buffer[i].length; j++) {
          if (buffer[i][j] == null) {
            screen.setCharacter(i, j, TextCharacter.DEFAULT_CHARACTER);
          } else {
            screen.setCharacter(i, j, buffer[i][j]);
          }
        }
      }
      screen.refresh();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
