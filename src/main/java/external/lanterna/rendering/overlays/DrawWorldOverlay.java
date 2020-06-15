package external.lanterna.rendering.overlays;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import external.lanterna.rendering.RenderingOverlay;
import external.lanterna.rendering.lighting.LightingLevel;
import external.lanterna.rendering.lighting.LightingShader;
import external.lanterna.rendering.visitor.DrawingVisitor;
import external.lanterna.rendering.visitor.FindPlayerOrDefaultVisitor;
import stardeath.world.World;

/**
 * An implementation of a {@link RenderingOverlay} that makes use of some {@link LightingShader} and
 * a {@link DrawingVisitor} to draw all the contents of the game on the screen. This is generally
 * the base overlay that you will want to draw onto.
 */
public class DrawWorldOverlay implements RenderingOverlay {

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(Screen screen, World world) {

    TerminalSize currentSize = screen.getTerminalSize();

    DrawingVisitor render = new DrawingVisitor(world.getWidth(), world.getHeight());
    FindPlayerOrDefaultVisitor findPlayer = new FindPlayerOrDefaultVisitor();
    LightingShader shader = new LightingShader(world);

    // Render the floor and the participants.
    world.visitTiles(render);
    world.visitAnimates(render);

    // Light the level.
    world.visitAnimates(shader);

    // Find the player.
    world.visitAnimates(findPlayer);

    // Calculate the lighting.
    LightingLevel[][] lighting = shader.getLevels();

    // Offset the screen appropriately.
    int offsetX = findPlayer.getPlayer().getPosition().getX() - currentSize.getColumns() / 2;
    int offsetY = findPlayer.getPlayer().getPosition().getY() - currentSize.getRows() / 2;

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
  }
}
