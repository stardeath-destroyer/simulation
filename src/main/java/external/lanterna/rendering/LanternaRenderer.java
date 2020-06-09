package external.lanterna.rendering;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import external.lanterna.rendering.lighting.LightingShader;
import java.io.IOException;
import java.util.Collection;
import stardeath.interactions.Renderer;
import stardeath.participants.Participant;
import stardeath.participants.player.Player;
import stardeath.world.Floor;

public class LanternaRenderer implements Renderer {

  private final Screen screen;
  private TerminalSize currentSize;

  public LanternaRenderer(Terminal terminal, Screen screen) throws IOException {
    this.screen = screen;
    this.screen.startScreen();
    this.currentSize = terminal.getTerminalSize();

    terminal.addResizeListener((t, size) -> LanternaRenderer.this.currentSize = size);
  }

  @Override
  public void render(Floor floor, Collection<Participant> players) {

    RenderingVisitor render = new RenderingVisitor(floor.getWidth() + 1, floor.getHeight() + 1);
    LightingShader shader = new LightingShader(floor.getWidth() + 1, floor.getHeight() + 1);

    // Visit the floor and the participants.
    floor.visit(render);
    players.forEach(p -> p.accept(render));

    // Calculate the lighting.
    // TODO : For ray tracing, we may want to visit the shader with all the tiles and keep track
    //        of the opaque ones.
    render.getPlayer().ifPresent(shader::withPlayer);

    // We MUST have at least one player.
    Player player = render.getPlayer().orElseThrow();

    // Offset the screen appropriately.
    int offsetX = player.getX() - currentSize.getColumns() / 2;
    int offsetY = player.getY() - currentSize.getRows() / 2;

    // Render the buffer with its offset.
    try {
      for (int x = 0; x < currentSize.getColumns(); x++) {
        for (int y = 0; y < currentSize.getRows(); y++) {
          int bx = offsetX + x;
          int by = offsetY + y;

          boolean insideBuffer = bx >= 0 && by >= 0 &&
              bx < floor.getWidth() + 1 && by < floor.getHeight() + 1;

          if (insideBuffer && render.getDrawn()[bx][by]) {
            screen.setCharacter(x, y, new TextCharacter(
                render.getCharacters()[bx][by],
                render.getForeground()[bx][by].lighted(shader.getLighting()[bx][by]),
                render.getBackground()[bx][by].lighted(shader.getLighting()[bx][by])
            ));
          } else {
            screen.setCharacter(x, y, TextCharacter.DEFAULT_CHARACTER);
          }
        }
      }

      screen.refresh();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
