package external.lanterna.rendering;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import external.lanterna.rendering.overlays.AimingOverlay;
import external.lanterna.rendering.overlays.DrawWorldOverlay;
import external.lanterna.rendering.overlays.EndingTextOverlay;
import external.lanterna.rendering.overlays.FloorStatisticsOverlay;
import external.lanterna.rendering.overlays.HealthOverlay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.Renderer;
import stardeath.world.World;

/**
 * An implementation of a {@link Renderer} that provides a way to {@link GetDirections}. Internally,
 * it makes use of a {@link List} of {@link RenderingOverlay} instances, displayed on top of each
 * other.
 */
public class LanternaRenderer implements GetDirections, AimingOverlay.Aiming, Renderer {

  /**
   * Th {@link Screen} to which content is rendered.
   */
  private final Screen screen;

  /**
   * Thee {@link stardeath.controller.interactions.Renderer.OnRenderRequestListener} that we are
   * interested in notifying.
   */
  private final List<OnRenderRequestListener> listeners = new ArrayList<>();

  /**
   * The {@link RenderingOverlay}s that are used.
   */
  private final List<RenderingOverlay> overlays = new ArrayList<>();

  /**
   * True if the player is currently aiming at something.
   */
  private boolean isAiming;

  public LanternaRenderer(Terminal terminal, Screen screen) throws IOException {
    this.screen = screen;
    this.screen.startScreen();
    this.isAiming = false;

    // Ensure we're notified on resizes.
    terminal.addResizeListener((t, size) -> {
      screen.doResizeIfNecessary();
      for (OnRenderRequestListener listener : listeners) {
        listener.requestRender();
      }
    });

    // Setup all the overlays
    this.overlays.add(new DrawWorldOverlay());
    this.overlays.add(new AimingOverlay(this));
    this.overlays.add(new HealthOverlay());
    this.overlays.add(new FloorStatisticsOverlay());
    this.overlays.add(new EndingTextOverlay());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ProjectileDirection requestDirectionsFromPlayer() {
    isAiming = true;
    listeners.forEach(OnRenderRequestListener::requestRender);

    try {
      while (true) {
        KeyStroke stroke = screen.readInput();
        Character character = stroke != null
            ? stroke.getCharacter()
            : null;
        ProjectileDirection direction = character != null
            ? ProjectileDirection.fromCharacter(character)
            : null;
        if (direction != null) {
          isAiming = false;
          return direction;
        }
      }
    } catch (IOException exception) {
      throw new IllegalStateException("Something went wrong.");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(World world) {
    try {

      // Render all the overlays.
      for (RenderingOverlay overlay : overlays) {
        overlay.render(screen, world);
      }
      screen.refresh();

    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerRenderRequestListener(OnRenderRequestListener listener) {
    if (!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unregisterRenderRequestListener(OnRenderRequestListener listener) {
    if (listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAiming() {
    return isAiming;
  }
}
