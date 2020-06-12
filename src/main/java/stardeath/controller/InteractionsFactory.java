package stardeath.controller;

import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.controller.interactions.Renderer;

/**
 * An interface representing a factory for the different kind of interactions that the user may have
 * with the program. Such interactions include user inputs, represented by Get-prefixed interfaces,
 * and the {@link Renderer}, in charge of drawing the contents to the screen.
 */
public interface InteractionsFactory {

  /**
   * Returns a {@link GetDirections} instance, that allows for synchronous polling of some
   * directions that the user selects.
   */
  GetDirections direction();

  /**
   * Returns a {@link GetMovements} instance, that allows for synchronous polling of some movement
   * that the user wants to do with the main player.
   */
  GetMovements movement();

  /**
   * Returns the {@link Renderer} that will be in charge of displaying the game to the user
   * (probably to the screen).
   */
  Renderer renderer();
}
