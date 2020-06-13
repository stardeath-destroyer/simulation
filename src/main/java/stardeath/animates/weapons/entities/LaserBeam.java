package stardeath.animates.weapons.entities;

import java.util.concurrent.atomic.AtomicBoolean;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.Projectile;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.animates.weapons.visitors.HitDamageVisitor;
import stardeath.world.Vector;

public class LaserBeam extends Projectile {

  private static final int DAMAGE = 50;

  private boolean dispersed;

  public LaserBeam(Vector position, ProjectileDirection direction) {
    super(position, direction, 1);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  @Override
  protected boolean isDispersed() {
    return dispersed;
  }

  public class MoveAndHit extends MoveAndConsume {

    public MoveAndHit() {
      super((world, position) -> {
        HitDamageVisitor visitor = new HitDamageVisitor(LaserBeam.DAMAGE);
        world.participantAt(position).ifPresent(p -> p.accept(visitor));
        world.tileAt(position).ifPresent(t -> {
          if (t.isOpaque()) {
            dispersed = true;
          }
        });
        dispersed = dispersed || visitor.isConsumed();
      });
    }
  }
}
