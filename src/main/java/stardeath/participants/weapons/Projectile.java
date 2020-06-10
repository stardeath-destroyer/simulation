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

  public Projectile(int x, int y, Direction direction, int speed) {
    super(x, y);
    this.direction = direction;
    this.speed = speed;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  public enum Direction {
    ZERO(List.of(new Vector(1, 0), new Vector(1, 0)));
    //ONE,
    //TWO,
    //THREE,
    //FOUR,
    //FIVE,
    //SIX,
    //SEVEN,
    //EIGHT,
    //NINE,
    //A,
    //B,
    //C,
    //D,
    //E,
    //F;

    private final List<Vector> steps;

    /* private */ Direction(List<Vector> steps) {
      this.steps = steps;
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
          base = base.add(step);
          x = base.getX();
          y = base.getY();
          ConsumeProjectileVisitor consumeProjectileVisitor = new ConsumeProjectileVisitor(10);
          level.getParticipant(base.getX(), base.getY())
              .ifPresent(a -> a.accept(consumeProjectileVisitor));

          if (consumeProjectileVisitor.isConsumed()) {
            remove();
          }

          level.getTile(base.getX(), base.getY())
              .ifPresent(tile -> {
                if (tile.isOpaque()) {
                  remove();
                }
              });
        }
      }
    }
  }
}
