package external.lanterna.rendering;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
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

    TextCharacter[][] buffer = new TextCharacter[floor.getWidth() + 1][floor.getHeight() + 1];

    RenderFloor renderFloor = new RenderFloor(buffer);
    RenderParticipants renderParticipants = new RenderParticipants(buffer);

    // Visit the floor and the participants.
    floor.visit(renderFloor);
    players.forEach(p -> p.accept(renderParticipants));

    // We MUST have at least one player.
    Player player = renderParticipants.getPlayer().orElseThrow();

    // Gray out the tiles that are not close to the player.
    floor.getTiles().stream()
        .filter(tile -> tile.distanceTo(player.getX(), player.getY()) > player.getVisibilityRange())
        .forEach(tile -> {
          TextCharacter character = buffer[tile.getX()][tile.getY()];
          if (character != null) {
            TextColor foreground = character.getForegroundColor();
            TextColor background = character.getBackgroundColor();

            // TODO : We might want to support changing the foreground and the background colors
            //        simultaneously.
            if (background != ANSI.DEFAULT) {
              background = TextColor.Indexed.fromRGB(100, 100, 100);
            } else {
              foreground = TextColor.Indexed.fromRGB(100, 100, 100);
            }

            buffer[tile.getX()][tile.getY()] =
                buffer[tile.getX()][tile.getY()]
                    .withForegroundColor(foreground)
                    .withBackgroundColor(background);
          }
        });

    // Offset the screen appropriately.
    int offsetX = player.getX() - currentSize.getColumns() / 2;
    int offsetY = player.getY() - currentSize.getRows() / 2;

    // Render the buffer with its offset.
    try {
      for (int x = 0; x < currentSize.getColumns(); x++) {
        for (int y = 0; y < currentSize.getRows(); y++) {
          int bx = offsetX + x;
          int by = offsetY + y;

          boolean insideBuffer = bx >= 0 && by >= 0
              && bx < buffer.length && by < buffer[bx].length;

          TextCharacter retrieved = insideBuffer && buffer[bx][by] != null
              ? buffer[bx][by]
              : TextCharacter.DEFAULT_CHARACTER;

          screen.setCharacter(x, y, retrieved);
        }
      }

      screen.refresh();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
