package external.lanterna.rendering;

import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.Collection;
import stardeath.interactions.Renderer;
import stardeath.participants.Participant;
import stardeath.world.Floor;

public class LanternaRenderer implements Renderer {

  private final Screen screen;
  private final RenderFloor renderFloor;
  private final RenderParticipants renderParticipants;

  public LanternaRenderer(Screen screen) throws IOException {
    this.screen = screen;
    this.screen.startScreen();
    this.renderFloor = new RenderFloor(screen);
    this.renderParticipants = new RenderParticipants(screen);
  }

  @Override
  public void render(Floor floor, Collection<Participant> players) {

    floor.visit(renderFloor);
    players.forEach(p -> p.accept(renderParticipants));

    try {
      screen.refresh();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
