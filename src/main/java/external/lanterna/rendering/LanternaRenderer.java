package external.lanterna.rendering;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import external.lanterna.rendering.lighting.LightingLevel;
import external.lanterna.rendering.lighting.LightingShader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import stardeath.interactions.Renderer;
import stardeath.participants.Participant;
import stardeath.participants.player.Player;
import stardeath.world.Floor;

public class LanternaRenderer implements Renderer {

  private final Screen screen;
  private TerminalSize currentSize;
  private final List<OnRenderRequestListener> listeners = new ArrayList<>();

  public LanternaRenderer(Terminal terminal, Screen screen) throws IOException {
    this.screen = screen;
    this.screen.startScreen();
    this.currentSize = terminal.getTerminalSize();

    terminal.addResizeListener((t, size) -> {
      LanternaRenderer.this.currentSize = size;
      screen.doResizeIfNecessary();
      for (OnRenderRequestListener listener : listeners) {
        listener.requestRender();
      }
    });
  }

  @Override
  public void render(Floor floor) {

    RenderingVisitor render = new RenderingVisitor(floor.getWidth() + 1, floor.getHeight() + 1);
    LightingShader shader = new LightingShader(floor);

    // Visit the floor and the participants.
    floor.visitTiles(render);
    floor.visitParticipants(render);


    // We MUST have at least one player.
    Player player = render.getPlayer().orElseThrow();

    // Calculate the lighting.
    LightingLevel[][] lighting = shader.withPlayer(player);

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
                render.getForeground()[bx][by].lighted(lighting[bx][by]),
                render.getBackground()[bx][by].lighted(lighting[bx][by])
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

  @Override
  public void registerRenderRequestListener(OnRenderRequestListener listener) {
    if (!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  @Override
  public void unregisterRenderRequestListener(OnRenderRequestListener listener) {
    if (listeners.contains(listener)) {
      listeners.add(listener);
    }
  }
}
