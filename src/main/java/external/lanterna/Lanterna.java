package external.lanterna;

import external.cli.interactions.InputStreamMovementInteractions;
import external.lanterna.rendering.LanternaRenderer;
import java.io.IOException;
import stardeath.UIFactory;
import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;

public class Lanterna implements UIFactory {

  @Override
  public MovementInteractions movement() {
    return new InputStreamMovementInteractions(System.in);
  }

  @Override
  public Renderer renderer() {
    try {
      return new LanternaRenderer();
    } catch (IOException exception) {
      throw new RuntimeException("Unable to use Lanterna on this device. Sorry.");
    }
  }
}
