package external.lanterna.interactions;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.Objects;
import stardeath.controller.interactions.Movement;
import stardeath.controller.interactions.GetMovements;

public class LanternaGetMovements implements GetMovements {

  private final Screen screen;

  public LanternaGetMovements(Screen screen) {
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
          } else if (stroke.getKeyType() == KeyType.Enter || Objects.equals('f', stroke.getCharacter())) {
            movement = Movement.FIRE;
          } else if (Objects.equals('e', stroke.getCharacter())) {
            movement = Movement.INTERACT;
          } else if (Objects.equals('g', stroke.getCharacter())) {
            movement = Movement.GRENADE;
          }
        }
      } catch (IOException any) {
        // Too bad.
      }
    }
    return movement;
  }
}
