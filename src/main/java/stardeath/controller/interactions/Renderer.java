package stardeath.controller.interactions;

import stardeath.controller.Controller;
import stardeath.world.Floor;

/**
 * An interface describing an element that is able to render the game state to the screen and
 * display the status to the user.
 */
public interface Renderer {

  /**
   * Renders a certain {@link Floor} to the screen, along with its contents.
   *
   * @param floor The {@link Floor} to be rendered.
   */
  void render(Floor floor);


  /**
   * Registers an {@link OnRenderRequestListener} to this {@link Renderer}.
   *
   * @param listener The listener that's registered.
   */
  default void registerRenderRequestListener(OnRenderRequestListener listener) {
  }

  /**
   * Unregisters an {@link OnRenderRequestListener} from this {@link Renderer}.
   *
   * @param listener The listener that's unregistered.
   */
  default void unregisterRenderRequestListener(OnRenderRequestListener listener) {
  }

  /**
   * Some {@link Renderer}s have the ability to perform immediate renders, outside of what the
   * {@link Controller} asks. To profit from this ability, the {@link
   * Controller} registers itself as an {@link OnRenderRequestListener} to the {@link
   * Renderer} to be notified of requests to draw the game state.
   */
  interface OnRenderRequestListener {

    /**
     * This method should be called whenever a new render request is performed.
     */
    void requestRender();
  }
}
