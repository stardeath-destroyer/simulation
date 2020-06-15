package external.lanterna.rendering.overlays;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.TextColor.Indexed;
import com.googlecode.lanterna.screen.Screen;
import external.lanterna.rendering.RenderingOverlay;
import stardeath.world.World;

/**
 * An implementation of a {@link RenderingOverlay} that displays the ending text to the screen.
 */
public class EndingTextOverlay implements RenderingOverlay {

  /**
   * A matrix with the message if the game is lost.
   */
  private static final int[][] YOU_LOST = {
      {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0,
          0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0},
      {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
          0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
      {1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1,
          1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1},
      {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
          0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
      {1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
          0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0},
  };

  /**
   * A matrix with the message if the game is won.
   */
  private static final int[][] YOU_WON = {
      {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0,
          0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1,
          1, 1, 0, 1, 0, 1},
      {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
          0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0,
          1, 0, 0, 1, 0, 1},
      {1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1,
          1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0,
          1, 0, 0, 1, 1, 1},
      {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
          0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0,
          1, 0, 0, 1, 0, 1},
      {1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
          0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0,
          1, 0, 0, 1, 0, 1},
  };

  /**
   * Renders an ASCII representation of some text to the screen. This will transpose the text to the
   * right place, and render it in yellow with a slightly altered color.
   *
   * @param text   Thee screen to render, in ASCII grid format.
   * @param screen The {@link Screen} to render to.
   */
  private void renderMostlyCentered(int[][] text, Screen screen) {

    // Retrieve the size.
    int width = screen.getTerminalSize().getColumns();
    int height = screen.getTerminalSize().getRows();

    // Handle the text grid.
    int maxHeight = text.length;
    int maxWidth = 0;
    for (int[] row : text) {
      maxWidth = Math.max(row.length, maxWidth);
    }

    // Calculate the offsets.
    int left = width / 2 - maxWidth / 2;
    int top = height / 2 - maxHeight / 2;

    // Draw the background.
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        screen.setCharacter(i, j, new TextCharacter(' ', ANSI.BLACK, ANSI.BLACK));
      }
    }

    // Draw the text.
    for (int i = 0; i < text.length; i++) {
      for (int j = 0; j < text[i].length; j++) {
        int x = j + left;
        int y = i + top;
        if (x >= 0 && x < width && y >= 0 && y < height) {
          if (text[i][j] == 1) {
            if ((x + y) % 2 == 0) {
              screen.setCharacter(x, y, new TextCharacter(' ', new Indexed(190), new Indexed(190)));
            } else {
              screen.setCharacter(x, y, new TextCharacter(' ', new Indexed(191), new Indexed(191)));
            }
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(Screen screen, World world) {
    switch (world.getState()) {
      case SAVED:
        renderMostlyCentered(YOU_LOST, screen);
        break;
      case DESTROYED:
        renderMostlyCentered(YOU_WON, screen);
        break;
    }
  }
}
