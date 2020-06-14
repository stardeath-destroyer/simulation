package stardeath.animates.weapons.entities;

import stardeath.animates.actions.Action;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.ParticipantVisitor;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.Projectile;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.animates.weapons.visitors.ConsumableVisitor;
import stardeath.world.Vector;
import stardeath.world.World;

public class Grenade extends Projectile {

  private static final int RANGE = 3;
  private static final int DAMAGE = 150;
  private static final int SPEED_FACTOR = 1;

  private boolean willExplode;
  private boolean exploding;

  public Grenade(Vector position, ProjectileDirection direction) {
    super(position, direction, SPEED_FACTOR);
  }

  public int getRange() {
    return RANGE;
  }

  public boolean willExplode() {
    return willExplode;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  public class MoveAndTrigger extends MoveAndConsume {

    public MoveAndTrigger(ConsumableVisitor consumableVisitor) {
      super(consumableVisitor);
    }

    /*
    public MoveAndTrigger() {
      super((world, vector) -> willExplode = true);
    }

     */
  }

  public class Explode implements Action {

    @Override
    public void execute(World world) {
      Grenade.this.willExplode = false;
      world.visitVisibleAnimatesFrom(Grenade.this, Grenade.RANGE, new ParticipantVisitor() {
        @Override
        public void visit(Participant participant) {
          participant.damage(DAMAGE);
        }
      });
      remove();
    }
  }
}
