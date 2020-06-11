package external.cli;

import external.cli.interactions.InputStreamMovementInteractions;
import external.cli.rendering.OutputStreamRenderer;
import stardeath.InteractionsFactory;
import stardeath.interactions.GetDirections;
import stardeath.interactions.GetMovements;
import stardeath.interactions.Renderer;

public class CLI implements InteractionsFactory {

  @Override
  public GetDirections direction() {
    return new InputStreamMovementInteractions(System.in);
  }

  @Override
  public GetMovements movement() {
    return new InputStreamMovementInteractions(System.in);
  }

  @Override
  public Renderer renderer() {
    return new OutputStreamRenderer(System.out);
  }
}
