package stardeath.interactions;

import stardeath.world.Floor;

public interface Renderer {

  void render(Floor floor);

  default void registerRenderRequestListener(OnRenderRequestListener listener) {
  }

  default void unregisterRenderRequestListener(OnRenderRequestListener listener) {
  }

  interface OnRenderRequestListener {

    void requestRender();
  }
}
