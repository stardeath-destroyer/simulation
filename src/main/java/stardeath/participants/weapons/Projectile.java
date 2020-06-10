package stardeath.participants.weapons;

import stardeath.animates.Animate;
import stardeath.animates.AnimateVisitor;

public class Projectile extends Animate {

  public Projectile(int x, int y) {
    super(x, y);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }
}
