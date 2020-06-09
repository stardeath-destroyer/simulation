package external.lanterna.rendering;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.Collection;
import stardeath.interactions.Renderer;
import stardeath.participants.Participant;
import stardeath.participants.player.Player;
import stardeath.world.Floor;

public class LanternaRenderer implements Renderer {

  private TerminalSize currentSize;
  private final Screen screen;

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

    floor.visit(renderFloor);
    players.forEach(p -> p.accept(renderParticipants));

    int offsetX = renderParticipants.getPlayer()
        .map(Player::getX)
        .orElse(0) - currentSize.getColumns() / 2;

    int offsetY = renderParticipants.getPlayer()
        .map(Player::getY)
        .orElse(0) - currentSize.getRows() / 2;

    try {
      for (int x = 0; x < currentSize.getColumns(); x++) {
        for (int y = 0; y < currentSize.getRows(); y++) {
          int bx = offsetX + x ;
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
