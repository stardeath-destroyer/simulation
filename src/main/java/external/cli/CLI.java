package external.cli;

import external.cli.interactions.InputStreamMovementInteractions;
import external.cli.rendering.OutputStreamRenderer;
import stardeath.UIFactory;
import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;

public class CLI implements UIFactory {

  @Override
  public MovementInteractions movement() {
    return new InputStreamMovementInteractions(System.in);
  }

  @Override
  public Renderer renderer() {
    return new OutputStreamRenderer(System.out);
  }
}
