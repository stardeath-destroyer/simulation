package stardeath.animates.weapons.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.Projectile;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.animates.weapons.visitors.HitDamageVisitor;
import stardeath.world.Vector;

/**
 * Represents a laser beam
 */
public class LaserBeam extends Projectile {

  private static final int DAMAGE = 50;

  public LaserBeam(Vector position, ProjectileDirection direction) {
    super(position, direction, 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  /**
   * Hits a target and then is consumed
   */
  public class MoveAndHit extends MoveAndConsume {

    public MoveAndHit() {
      super(new HitDamageVisitor(LaserBeam.this, DAMAGE));
    }
  }
}
