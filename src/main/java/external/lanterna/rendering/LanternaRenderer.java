package external.lanterna.rendering;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.TextColor.Indexed;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import external.lanterna.rendering.lighting.LightingLevel;
import external.lanterna.rendering.lighting.LightingShader;
import external.lanterna.rendering.overlays.HealthOverlay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.Renderer;
import stardeath.world.World;

public class LanternaRenderer implements GetDirections, Renderer {

  private final Screen screen;
  private final List<OnRenderRequestListener> listeners = new ArrayList<>();
  private final List<RenderingOverlay> overlays = new ArrayList<>();
  private TerminalSize currentSize;
  private boolean displayOverlay;

  public LanternaRenderer(Terminal terminal, Screen screen) throws IOException {
    this.screen = screen;
    this.screen.startScreen();
    this.currentSize = terminal.getTerminalSize();
    this.displayOverlay = false;

    this.overlays.add(new HealthOverlay());

    terminal.addResizeListener((t, size) -> {
      LanternaRenderer.this.currentSize = size;
      screen.doResizeIfNecessary();
      for (OnRenderRequestListener listener : listeners) {
        listener.requestRender();
      }
    });
  }

  @Override
  public ProjectileDirection requestDirectionsFromPlayer() {
    displayOverlay = true;
    listeners.forEach(OnRenderRequestListener::requestRender);

    try {
      while (true) {
        KeyStroke stroke = screen.readInput();
        Character character = stroke != null ? stroke.getCharacter() : null;
        ProjectileDirection direction =
            character != null ? ProjectileDirection.fromCharacter(character) : null;
        if (direction != null) {
          displayOverlay = false;
          return direction;
        }
      }
    } catch (IOException exception) {
      throw new IllegalStateException("Something went wrong.");
    }
  }

  private void renderText(int[][] text) {
    boolean[][] data = TextUtils.renderMostlyCentered(
        text,
        currentSize.getColumns(),
        currentSize.getRows());

    for (int x = 0; x < currentSize.getColumns(); x++) {
      for (int y = 0; y < currentSize.getRows(); y++) {
        if (data[x][y]) {
          if ((x + y) % 2 == 0) {
            screen.setCharacter(x, y, new TextCharacter(' ', new Indexed(190), new Indexed(190)));
          } else {
            screen.setCharacter(x, y, new TextCharacter(' ', new Indexed(191) ,new Indexed(191)));
          }
        } else {
          screen.setCharacter(x, y, new TextCharacter(' '));
        }
      }
    }

    try {
      screen.refresh();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private void renderLost() {
    renderText(TextUtils.YOU_LOST);
  }

  private void renderWon() {
    renderText(TextUtils.YOU_WON);
  }

  @Override
  public void render(World world) {

    switch (world.getState()) {
      case DESTROYED:
        renderWon();
        return;
      case SAVED:
        renderLost();
        return;
    }

    RenderingVisitor render = new RenderingVisitor(world.getWidth(), world.getHeight());
    LightingShader shader = new LightingShader(world);

    // Render the floor and the participants.
    world.visitTiles(render);
    world.visitAnimates(render);

    // Light the level.
    world.visitAnimates(shader);

    // We MUST have at least one player.
    Player player = render.getPlayer().orElseThrow();

    // Calculate the lighting.
    LightingLevel[][] lighting = shader.getLevels();

    // Offset the screen appropriately.
    int offsetX = player.getPosition().getX() - currentSize.getColumns() / 2;
    int offsetY = player.getPosition().getY() - currentSize.getRows() / 2;

    // Render the buffer with its offset.
    try {
      for (int x = 0; x < currentSize.getColumns(); x++) {
        for (int y = 0; y < currentSize.getRows(); y++) {
          int bx = offsetX + x;
          int by = offsetY + y;

          boolean insideBuffer = bx >= 0 && by >= 0 &&
              bx < world.getWidth() && by < world.getHeight();

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

      // We want to display an overlay here as well.
      if (displayOverlay) {
        renderPlayerOverlay(
            player.getPosition().getX() - offsetX,
            player.getPosition().getY() - offsetY);
      }

      for (RenderingOverlay overlay : overlays) {
        overlay.render(screen, world);
      }

      screen.refresh();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private void renderPlayerOverlay(int centerX, int centerY) {
    if (currentSize.getRows() < 5 || currentSize.getColumns() < 5) {
      // We won't be able to display the player overlay if the size of the terminal is smaller
      // than 5 lines.
      return;
    }

    Character[][] overlay = {
        {'6', '5', '4', '3', '2'},
        {'7', null, null, null, '1'},
        {'8', null, null, null, '0'},
        {'9', null, null, null, 'F'},
        {'A', 'B', 'C', 'D', 'E'}
    };

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        int x = centerX - 2 + j;
        int y = centerY - 2 + i;
        if (overlay[i][j] != null && (i + j) % 2 == 0) {
          screen.setCharacter(x, y,
              new TextCharacter(overlay[i][j], new TextColor.Indexed(196), ANSI.DEFAULT));
        } else if (overlay[i][j] != null) {
          screen.setCharacter(x, y,
              new TextCharacter(overlay[i][j], new TextColor.Indexed(200), ANSI.DEFAULT));
        }
      }
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
