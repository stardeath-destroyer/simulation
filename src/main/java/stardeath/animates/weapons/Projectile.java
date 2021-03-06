package stardeath.animates.weapons;

import stardeath.animates.Action;
import stardeath.animates.Animate;
import stardeath.animates.visitors.DefaultAnimateVisitor;
import stardeath.animates.weapons.visitors.ConsumableVisitor;
import stardeath.world.Vector;
import stardeath.world.World;

/**
 * This class represents a projectile
 */
public abstract class Projectile extends Animate {

  private final ProjectileDirection direction;
  private final int speed;

  public Projectile(Vector position, ProjectileDirection direction, int speed) {
    super(position);
    this.direction = direction;
    this.speed = speed;
  }

  /**
   * Get the direction in which the projectile is going
   * @return the direction in which the projectile is going
   */
  public ProjectileDirection getDirection() {
    return direction;
  }

  /**
   * An Action that will move a projectile and consume it on certain circumstances
   */
  public class MoveAndConsume implements Action {

    private final ConsumableVisitor consumableVisitor;

    public MoveAndConsume(ConsumableVisitor consumableVisitor) {
      this.consumableVisitor = consumableVisitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(World world) {
      for (int i = 0; i < speed; i++) {
        for (Vector step : direction.getSteps()) {

          // Apply the damage to whatever is on the path of the projectile.
          if (!consumableVisitor.isConsumed()) {
            world.visitAnimateAt(position, new DefaultAnimateVisitor(animate -> {
              if (animate != Projectile.this)
                animate.accept(consumableVisitor);
            }));
          }
          
          // If the visitor hasn't been consumed yet, continue
          if (!consumableVisitor.isConsumed()) {
            world.visitTileAt(position, consumableVisitor);
          }

          if (consumableVisitor.isConsumed()) {
            return;
          }

          // Move by one of the internal direction step vectors.
          position = getPosition().add(step);
        }
      }
    }
  }
}
