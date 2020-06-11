package stardeath.participants.weapons;

import java.util.List;
import stardeath.animates.Animate;
import stardeath.animates.AnimateVisitor;
import stardeath.participants.actions.Action;
import stardeath.world.Floor;
import stardeath.world.Vector;

public class Projectile extends Animate {

  private final Direction direction;
  private final int speed;
  private final int damage;

  public Projectile(int x, int y, int damage, Direction direction, int speed) {
    super(x, y);
    this.damage = damage;
    this.direction = direction;
    this.speed = speed;
  }

  public Direction getDirection() {
    return direction;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  public enum Direction {
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

    /* private */ Direction(char character, List<Vector> steps) {
      this.character = character;
      this.steps = steps;
    }

    public static Direction fromCharacter(char character) {
      for (Direction direction : values()) {
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

  public class MoveAndHit implements Action {

    @Override
    public void execute(Floor level) {
      Vector base = new Vector(getX(), getY());
      for (int i = 0; i < speed; i++) {
        for (Vector step : direction.getSteps()) {

          // Move by one of the internal direction step vectors.
          base = base.add(step);

          // Update the position of the head of this projectile.
          x = base.getX();
          y = base.getY();

          // Apply the damage to whatever is on the path of the projectile.
          ConsumeProjectileVisitor consumeProjectileVisitor = new ConsumeProjectileVisitor(damage);
          level.getParticipant(x, y).ifPresent(a -> a.accept(consumeProjectileVisitor));

          // Remove the projectile if it has hit a participant already.
          if (consumeProjectileVisitor.isConsumed()) {
            remove();
          }

          // If no participant was hit, maybe we have actually hit a wall. If so, remove this
          // projectile.
          level.getTile(base.getX(), base.getY()).ifPresent(tile -> {
            if (tile.isOpaque()) {
              remove();
            }
          });
        }
      }
    }
  }
}
