package external.lanterna.rendering.overlays;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.screen.Screen;
import external.lanterna.rendering.RenderingOverlay;
import external.lanterna.rendering.visitor.FloorStatisticsVisitor;
import java.util.stream.Stream;
import stardeath.world.World;

/**
 * An implementation of a {@link RenderingOverlay} that displays some information about the current
 * game state. A great showcase of the visitor pattern !
 */
public class FloorStatisticsOverlay implements RenderingOverlay {

  /**
   * The color used for the statistics display.
   */
  private final static TextColor COLOR = new TextColor.RGB(128, 43, 0);

  /**
   * Builds an array of {@link String} statistics for a given {@link World}.
   *
   * @param world The {@link World} to analyze. We generally look at the current level.
   * @return The built statistics, in user-friendly format.
   */
  private String[] buildStatistics(World world) {
    FloorStatisticsVisitor visitor = new FloorStatisticsVisitor();
    world.visitAnimates(visitor);
    world.visitTiles(visitor);
    return new String[]{
        "There are still " + visitor.getRemainingTerminals() + " out of " + visitor
            .getTotalTerminals() + " terminals up on this floor",
        visitor.getRemainingEmpireMembers() + " empire members are near you, and " + visitor
            .getRemainingRebels() + " rebels stand on this floor.",
        "",
        "Also, there are " + visitor.getThrownProjectiles() + " flying projectiles right now."
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(Screen screen, World world) {
    String[] text = buildStatistics(world);

    int width = Stream.of(text).map(String::length).max(Integer::compareTo).orElse(0);
    int height = text.length;

    // Ensure we fit the screen.
    if (screen.getTerminalSize().getRows() < height - 8 ||
        screen.getTerminalSize().getColumns() < width - 2) {
      return;
    }

    // Draw the text contents on the screen buffer.
    for (int y = 0; y < text.length; y++) {
      for (int x = 0; x < text[y].length(); x++) {
        screen.setCharacter(x + 1, screen.getTerminalSize().getRows() - 8 + y, new TextCharacter(
            text[y].charAt(x),
            COLOR,
            ANSI.BLACK
        ));
      }
    }
  }
}
