package external.lanterna.rendering;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.Collection;
import stardeath.interactions.Renderer;
import stardeath.participants.Participant;
import stardeath.participants.player.Player;
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

    TerminalSize size = screen.getTerminalSize();

    int offsetX = renderParticipants.getPlayer()
        .map(Player::getX)
        .orElse(0) - size.getColumns() / 2;

    int offsetY = renderParticipants.getPlayer()
        .map(Player::getY)
        .orElse(0) - size.getRows() / 2;

    try {

      for (int x = 0; x < size.getColumns(); x++) {
        for (int y = 0; y < size.getRows(); y++) {
          boolean condition = (offsetX >= 0 && offsetY >= 0 && offsetX < floor.getWidth() && offsetY < floor.getHeight());
          TextCharacter retrieved = condition
              ? buffer[x + offsetX][y + offsetY]
              : TextCharacter.DEFAULT_CHARACTER;
          screen.setCharacter(x, y, retrieved);

          if (!condition)
            System.out.println("x " + x + " y " + y + " ox " + offsetX + " oy " + offsetY);
        }
      }

      // for (int i = 0; i < buffer.length; i++) {
      //   for (int j = 0; j < buffer[i].length; j++) {
      //     if (buffer[i][j] == null) {
      //       screen.setCharacter(i, j, TextCharacter.DEFAULT_CHARACTER);
      //     } else {
      //       screen.setCharacter(i, j, buffer[i][j]);
      //     }
      //   }
      // }
      screen.refresh();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
