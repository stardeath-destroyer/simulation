package external.lanterna;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import external.lanterna.interactions.LanternaMovementInteractions;
import external.lanterna.rendering.LanternaRenderer;
import java.io.IOException;
import stardeath.UIFactory;
import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;

public class Lanterna implements UIFactory {

  private final Screen screen;

  public Lanterna() throws IOException {
    DefaultTerminalFactory factory = new DefaultTerminalFactory();
    Terminal terminal = factory.createTerminal();

    // Create and display a terminal screen.
    this.screen = new TerminalScreen(terminal);
  }

  @Override
  public MovementInteractions movement() {
    return new LanternaMovementInteractions(screen);
  }

  @Override
  public Renderer renderer() {
    try {
      return new LanternaRenderer(screen);
    } catch (IOException exception) {
      throw new RuntimeException("Unable to use Lanterna on this device. Sorry.");
    }
  }
}
