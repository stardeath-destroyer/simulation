package external.lanterna.interactions;

import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import stardeath.interactions.Movement;
import stardeath.interactions.MovementInteractions;

public class LanternaMovementInteractions implements MovementInteractions {

  private final Screen screen;

  public LanternaMovementInteractions(Screen screen) {
    this.screen = screen;
  }

  @Override
  public Movement requestMovement() {
    Movement movement = null;
    while (movement == null) {
      try {
        switch (screen.readInput().getCharacter()) {
          case 'w':
            movement = Movement.UP;
            break;
          case 'a':
            movement = Movement.LEFT;
            break;
          case 's':
            movement = Movement.DOWN;
            break;
          case 'd':
            movement = Movement.RIGHT;
            break;
        }
      } catch (IOException any) {
        // Too bad.
      }
    }
    return movement;
  }
}
