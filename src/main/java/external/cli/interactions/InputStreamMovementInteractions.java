package external.cli.interactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.controller.interactions.Movement;
import stardeath.animates.weapons.ProjectileDirection;

/**
 * {@inheritDoc}
 */
public class InputStreamMovementInteractions implements GetDirections, GetMovements {

  private final InputStream stream;

  public InputStreamMovementInteractions(InputStream stream) {
    this.stream = stream;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Movement requestMovement() {
    System.out.print("Enter your move (w, a, s, d, [f]ire, [e]levator) : ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    Movement movement = null;
    try {
      while (movement == null) {
        switch (reader.readLine()) {
          case "w":
            movement = Movement.UP;
            break;
          case "a":
            movement = Movement.LEFT;
            break;
          case "s":
            movement = Movement.DOWN;
            break;
          case "d":
            movement = Movement.RIGHT;
            break;
          case "f":
            movement = Movement.FIRE;
            break;
          case "g":
            movement = Movement.GRENADE;
            break;
          case "e":
            movement = Movement.INTERACT;
            break;
          default:
            System.out.print("Wrong command. Please enter a valid move (w, a, s, d) : ");
            System.out.flush();
            break;
        }
      }
    } catch (IOException any) {
      // Too bad.
    }
    return movement;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ProjectileDirection requestDirectionsFromPlayer() {
    System.out.println("Enter a direction (0-9A-F) :");
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    ProjectileDirection direction = null;
    try {
      while (true) {
        String line = reader.readLine();
        if (line.length() == 1) {
          char character = line.charAt(0);
          direction = ProjectileDirection.fromCharacter(character);
        }
        if (direction != null) {
          return direction;
        }
      }
    } catch (IOException any) {
      // Too bad.
    }
    return direction;
  }
}
