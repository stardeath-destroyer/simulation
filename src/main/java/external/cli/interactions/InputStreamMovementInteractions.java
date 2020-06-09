package external.cli.interactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import stardeath.interactions.Movement;
import stardeath.interactions.MovementInteractions;

public class InputStreamMovementInteractions implements MovementInteractions {

  private final InputStream stream;

  public InputStreamMovementInteractions(InputStream stream) {
    this.stream = stream;
  }

  @Override
  public Movement requestMovement() {
    System.out.print("Enter your move (w, a, s, d) : ");
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
}
