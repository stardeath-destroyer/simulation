package stardeath;

import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;

public interface UIFactory {

  MovementInteractions movement();

  Renderer renderer();
}
