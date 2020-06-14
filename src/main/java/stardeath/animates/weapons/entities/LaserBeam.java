package stardeath.animates.weapons.entities;

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
    dispersed = false;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  public class MoveAndHit extends MoveAndConsume {

    public MoveAndHit() {
      super(new HitDamageVisitor(DAMAGE));
    }

    /*
    // TODO: Fix this weird behaviour... does not work on tiles
    public MoveAndHit() {
      super((world, position) -> {
        HitDamageVisitor visitor = new HitDamageVisitor(LaserBeam.DAMAGE);

        // Effects on participants
        world.participantAt(position).ifPresent(p -> p.accept(visitor));
        dispersed = dispersed || visitor.isConsumed();

        // Effects on tiles
        if (! dispersed) {
          world.tileAt(position).ifPresent(t -> t.accept(visitor));
        }
        dispersed = dispersed || visitor.isConsumed();
      });
    }
     */
  }
}
