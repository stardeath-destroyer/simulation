package external.lanterna;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import external.lanterna.interactions.LanternaGetMovements;
import external.lanterna.rendering.LanternaRenderer;
import java.io.IOException;
import stardeath.controller.InteractionsFactory;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.controller.interactions.Renderer;

public class Lanterna implements InteractionsFactory {

  private final GetDirections getDirections;
  private final GetMovements getMovements;
  private final Renderer renderer;

  public Lanterna() throws IOException {
    DefaultTerminalFactory factory = new DefaultTerminalFactory()
        .setTerminalEmulatorTitle("Stardeath Destroyer");

    // Create and display a terminal screen.
    Terminal terminal = factory.createTerminal();
    Screen screen = new TerminalScreen(terminal);
    screen.setCursorPosition(null);

    // Set the different fields.
    LanternaRenderer lanternaRenderer = new LanternaRenderer(terminal, screen);
    this.getDirections = lanternaRenderer;
    this.getMovements = new LanternaGetMovements(screen);
    this.renderer = lanternaRenderer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GetDirections direction() {
    return getDirections;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GetMovements movement() {
    return getMovements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Renderer renderer() {
    return renderer;
  }
}
