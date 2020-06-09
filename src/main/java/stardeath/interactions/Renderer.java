package stardeath.interactions;

import java.util.Collection;
import stardeath.participants.Participant;
import stardeath.world.Floor;

public interface Renderer {

  void render(Floor floor, Collection<Participant> players);

  default void registerRenderRequestListener(OnRenderRequestListener listener) {
  }

  default void unregisterRenderRequestListener(OnRenderRequestListener listener) {
  }

  interface OnRenderRequestListener {

    void requestRender();
  }
}
