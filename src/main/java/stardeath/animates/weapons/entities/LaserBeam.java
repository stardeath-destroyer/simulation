package stardeath.animates.weapons.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.Projectile;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.world.Vector;

public class LaserBeam extends Projectile {

  public LaserBeam(Vector position, ProjectileDirection direction) {
    super(position, 50, direction, 1);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  @Override
  protected boolean isDispersed() {
    return false;
  }
}
