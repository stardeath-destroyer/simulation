package stardeath.controller.interactions;

import stardeath.animates.weapons.ProjectileDirection;

/**
 * An interface representing a way to get a projectile's direction from the player
 */
public interface GetDirections {

  /**
   * Asks the player for some form of input representing the wanted direction for a projectile
   * @return A new ProjectileDirection
   */
  ProjectileDirection requestDirectionsFromPlayer();
}
