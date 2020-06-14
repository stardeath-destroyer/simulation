package stardeath.animates.weapons;

import stardeath.animates.Animate;
import stardeath.animates.actions.Action;
import stardeath.animates.weapons.visitors.ConsumableVisitor;
import stardeath.world.Vector;
import stardeath.world.World;

public abstract class Projectile extends Animate {

  private final ProjectileDirection direction;
  private final int speed;

  public Projectile(Vector position, ProjectileDirection direction, int speed) {
    super(position);
    this.direction = direction;
    this.speed = speed;
  }

  public ProjectileDirection getDirection() {
    return direction;
  }

  public class MoveAndConsume implements Action {

    private final ConsumableVisitor consumableVisitor;

    public MoveAndConsume(ConsumableVisitor consumableVisitor) {
      this.consumableVisitor = consumableVisitor;
    }

    @Override
    public void execute(World world) {
      Vector base = getPosition();
      for (int i = 0; i < speed; i++) {
        for (Vector step : direction.getSteps()) {

          // Move by one of the internal direction step vectors.
          base = base.add(step);

          // Update the position of the head of this projectile.
          position = base;

          // Apply the damage to whatever is on the path of the projectile.
          world.participantAt(position).ifPresent(animate -> {
            if (animate != Projectile.this) {
              animate.accept(consumableVisitor);
            }
          });

          // If the visitor hasn't been consumed yet, continue
          if (!consumableVisitor.isConsumed()) {
            world.tileAt(position).ifPresent(tile -> tile.accept(consumableVisitor));
          }

          if (consumableVisitor.isConsumed() || shouldRemove()) {
            remove();
            return;
          }
        }
      }
    }
  }
}
