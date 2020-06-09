package stardeath.rendering.lanterna;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.Collection;
import stardeath.participants.Participant;
import stardeath.rendering.Renderer;
import stardeath.world.Floor;

public class LanternaRenderer implements Renderer {

  private final Screen screen;
  private final RenderFloor renderFloor;
  private final RenderParticipants renderParticipants;

  public LanternaRenderer() throws IOException {
    DefaultTerminalFactory factory = new DefaultTerminalFactory();
    Terminal terminal = factory.createTerminal();

    // Create and display a terminal screen.
    this.screen = new TerminalScreen(terminal);
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
