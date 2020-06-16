package external.lanterna.rendering.overlays;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.screen.Screen;
import external.lanterna.rendering.RenderingOverlay;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.world.World;

/**
 * A {@link RenderingOverlay} that is in charge of displaying the health layer of the player. This
 * is done with a health bar at the bottom of the screen, alongside with some small text that
 * indicates what this is about.
 */
public class HealthOverlay implements RenderingOverlay {

  /**
   * The characters used for the health bar.
   */
  private static final TextCharacter HEALTH_BAR_CHARACTER = new TextCharacter(
      ' ',
      ANSI.GREEN,
      ANSI.GREEN);

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(Screen screen, World world) {
    PlayerHealthVisitor visitor = new PlayerHealthVisitor();
    world.visitAnimates(visitor);

    int count = (int) Math.floor((screen.getTerminalSize().getColumns() - 2) * visitor.healthLevel);
    int row = screen.getTerminalSize().getRows() - 2;
    for (int column = 0; column < count; column++) {
      screen.setCharacter(column + 1, row, HEALTH_BAR_CHARACTER);
    }

    renderSubtitle(screen, (int) (100 * visitor.healthLevel));
  }

  /**
   * Renders the subtitle that is displayed above the health bar.
   *
   * @param screen The screen to render on.
   * @param remaining The percentage of HPs remaining.
   */
  private void renderSubtitle(Screen screen, int remaining) {
    final String contents = "Health (" + remaining + "%) :";

    // We can't draw on screens very small.
    if (screen.getTerminalSize().getRows() < 3 ||
        screen.getTerminalSize().getColumns() < contents.length() - 2) {
      return;
    }

    for (int column = 0; column < contents.length(); column++) {
      screen.setCharacter(column + 1,
          screen.getTerminalSize().getRows() - 3,
          new TextCharacter(contents.charAt(column), ANSI.GREEN, ANSI.BLACK));
    }
  }

  /**
   * An implementation of a {@link NoOpAnimateVisitor} that knows about the health level of the
   * player.
   */
  private static class PlayerHealthVisitor extends NoOpAnimateVisitor {

    private float healthLevel = 0f;

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitParticipant(Player player) {
      super.visitParticipant(player);
      healthLevel = Math.max(
          healthLevel,
          (float) player.getHealthPoints() / Player.MAX_HEALTH_POINTS
      );
    }
  }
}
