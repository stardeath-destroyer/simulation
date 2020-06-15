package external.lanterna.rendering;

import com.googlecode.lanterna.screen.Screen;
import stardeath.world.World;

/**
 * An interface that describes an overlay that can be displayed above a classical rendering loop.
 */
public interface RenderingOverlay {

  /**
   * Combines multiple {@link RenderingOverlay}s into a single instance.
   *
   * @param overlays The composite {@link RenderingOverlay}s.
   * @return A new {@link RenderingOverlay}.
   */
  static RenderingOverlay of(RenderingOverlay... overlays) {
    return ((screen, world) -> {
      for (RenderingOverlay overlay : overlays) {
        overlay.render(screen, world);
      }
    });
  }

  /**
   * Renders the overlay on a certain screen with a certain {@link World}.
   *
   * @param screen The {@link Screen} to render to.
   * @param world  The {@link World} to use for rendering.
   */
  void render(Screen screen, World world);
}
