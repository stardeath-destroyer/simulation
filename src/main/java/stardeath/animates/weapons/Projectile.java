package stardeath.animates.weapons;

import java.util.function.BiConsumer;
import stardeath.animates.Animate;
import stardeath.animates.actions.Action;
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

  protected abstract boolean isDispersed();

  public ProjectileDirection getDirection() {
    return direction;
  }

  public abstract class MoveAndConsume implements Action {

    private final BiConsumer<World, Vector> hitConsumer;

    public MoveAndConsume(BiConsumer<World, Vector> hitConsumer) {
      this.hitConsumer = hitConsumer;
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
              hitConsumer.accept(world, animate.getPosition());
            }
          });
          world.tileAt(position).ifPresent(tile -> {
            if (tile.isOpaque()) {
              hitConsumer.accept(world, tile.getPosition());
            }
          });

          if (isDispersed() || shouldRemove()) {
            remove();
            return;
          }
        }
      }
    }
  }
}
