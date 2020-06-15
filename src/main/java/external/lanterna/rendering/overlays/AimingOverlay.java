package external.lanterna.rendering.overlays;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.screen.Screen;
import external.lanterna.rendering.RenderingOverlay;
import stardeath.world.World;

/**
 * An implementation of a {@link RenderingOverlay} that displays an aiming window above the player.
 */
public class AimingOverlay implements RenderingOverlay {

  /**
   * The drawn overlay.
   */
  private static final Character[][] OVERLAY = {
      {'6', '5', '4', '3', '2'},
      {'7', null, null, null, '1'},
      {'8', null, null, null, '0'},
      {'9', null, null, null, 'F'},
      {'A', 'B', 'C', 'D', 'E'}
  };

  /**
   * An interface to communicate the aiming status.
   */
  private final Aiming aiming;

  public AimingOverlay(Aiming aiming) {
    this.aiming = aiming;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(Screen screen, World world) {
    if (screen.getTerminalSize().getRows() < 5 ||
        screen.getTerminalSize().getColumns() < 5 ||
        !(aiming.isAiming())) {
      // We won't be able to display the player overlay if the size of the terminal is smaller
      // than 5 lines.
      return;
    }

    // Find the player position.
    int centerX = screen.getTerminalSize().getColumns() / 2;
    int centerY = screen.getTerminalSize().getRows() / 2;

    // Draw the overlay.
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        int x = centerX - 2 + j;
        int y = centerY - 2 + i;
        if (OVERLAY[i][j] != null && (i + j) % 2 == 0) {
          screen.setCharacter(
              x, y,
              new TextCharacter(
                  OVERLAY[i][j],
                  new TextColor.Indexed(196),
                  ANSI.DEFAULT));
        } else if (OVERLAY[i][j] != null) {
          screen.setCharacter(
              x, y,
              new TextCharacter(
                  OVERLAY[i][j],
                  new TextColor.Indexed(200),
                  ANSI.DEFAULT));
        }
      }
    }
  }

  /**
   * An interface that lets you interact with something that might be currently aiming.
   */
  public interface Aiming {

    /**
     * True if an aiming overlay should be displayed, false otherwise.
     */
    boolean isAiming();
  }
}
