package external.lanterna.interactions;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.Objects;
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
        KeyStroke stroke = screen.readInput();
        if (stroke != null) {
          if (stroke.getKeyType() == KeyType.ArrowUp || Objects.equals('w', stroke.getCharacter())) {
            movement = Movement.UP;
          } else if (stroke.getKeyType() == KeyType.ArrowLeft || Objects.equals('a', stroke.getCharacter())) {
            movement = Movement.LEFT;
          } else if (stroke.getKeyType() == KeyType.ArrowDown || Objects.equals('s', stroke.getCharacter())) {
            movement = Movement.DOWN;
          } else if (stroke.getKeyType() == KeyType.ArrowRight || Objects.equals('d', stroke.getCharacter())) {
            movement = Movement.RIGHT;
          }
        }
      } catch (IOException any) {
        // Too bad.
      }
    }
    return movement;
  }
}
