package stardeath.animates.weapons;

import java.util.List;
import stardeath.world.Vector;

public enum ProjectileDirection {
  ZERO('0', List.of(Vector.EAST, Vector.EAST)),
  ONE('1', List.of(Vector.EAST, Vector.NORTH, Vector.EAST)),
  TWO('2', List.of(Vector.NORTH_EAST, Vector.NORTH_EAST)),
  THREE('3', List.of(Vector.NORTH, Vector.EAST, Vector.NORTH)),
  FOUR('4', List.of(Vector.NORTH, Vector.NORTH)),
  FIVE('5', List.of(Vector.NORTH, Vector.WEST, Vector.NORTH)),
  SIX('6', List.of(Vector.NORTH_WEST, Vector.NORTH_WEST)),
  SEVEN('7', List.of(Vector.WEST, Vector.NORTH, Vector.WEST)),
  EIGHT('8', List.of(Vector.WEST, Vector.WEST)),
  NINE('9', List.of(Vector.WEST, Vector.SOUTH, Vector.WEST)),
  A('a', List.of(Vector.SOUTH_WEST, Vector.SOUTH_WEST)),
  B('b', List.of(Vector.SOUTH, Vector.WEST, Vector.SOUTH)),
  C('c', List.of(Vector.SOUTH, Vector.SOUTH)),
  D('d', List.of(Vector.SOUTH, Vector.EAST, Vector.SOUTH)),
  E('e', List.of(Vector.SOUTH_EAST, Vector.SOUTH_EAST)),
  F('f', List.of(Vector.EAST, Vector.SOUTH, Vector.EAST));

  private final char character;
  private final List<Vector> steps;

  /* private */ ProjectileDirection(char character, List<Vector> steps) {
    this.character = character;
    this.steps = steps;
  }

  public static ProjectileDirection fromCharacter(char character) {
    for (ProjectileDirection direction : values()) {
      if (direction.character == Character.toLowerCase(character)) {
        return direction;
      }
    }
    return null;
  }

  public List<Vector> getSteps() {
    return steps;
  }
}
