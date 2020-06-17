package external.cli;

import external.cli.interactions.InputStreamMovementInteractions;
import external.cli.rendering.OutputStreamRenderer;
import stardeath.controller.InteractionsFactory;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.controller.interactions.Renderer;

/**
 * {@inheritDoc}
 */
public class CLI implements InteractionsFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public GetDirections direction() {
    return new InputStreamMovementInteractions(System.in);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GetMovements movement() {
    return new InputStreamMovementInteractions(System.in);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Renderer renderer() {
    return new OutputStreamRenderer(System.out);
  }
}
