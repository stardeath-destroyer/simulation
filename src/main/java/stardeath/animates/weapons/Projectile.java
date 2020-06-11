package stardeath.animates.weapons;

import stardeath.animates.Animate;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.actions.Action;
import stardeath.world.Floor;
import stardeath.world.Vector;

public class Projectile extends Animate {

  private final ProjectileDirection direction;
  private final int speed;
  private final int damage;

  public Projectile(int x, int y, int damage, ProjectileDirection direction, int speed) {
    super(x, y);
    this.damage = damage;
    this.direction = direction;
    this.speed = speed;
  }

  public ProjectileDirection getDirection() {
    return direction;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
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
