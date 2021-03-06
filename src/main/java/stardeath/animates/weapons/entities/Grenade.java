package stardeath.animates.weapons.entities;

import stardeath.animates.Action;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.ParticipantVisitor;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.Projectile;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.animates.weapons.visitors.WillExplodeVisitor;
import stardeath.world.Vector;
import stardeath.world.World;

/**
 * Represents a grenade that can be thrown and explode.
 */
public class Grenade extends Projectile {

  private static final int RANGE = 3;
  private static final int DAMAGE = 150;
  private static final int SPEED_FACTOR = 1;

  private boolean willExplode;
  private boolean exploding;

  public Grenade(Vector position, ProjectileDirection direction) {
    super(position, direction, SPEED_FACTOR);
  }

  /**
   * Get the range of the grenade
   * @return The range of the grenade
   */
  public int getRange() {
    return RANGE;
  }

  /**
   * Will make the grenade explode on the next turn
   */
  public void trigger() {
    willExplode = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean shouldRemove() {
    return exploding;
  }

  /**
   * Is the grenade about to explode
   * @return True if the grenade will explode during the round
   */
  public boolean willExplode() {
    return willExplode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitProjectile(this);
  }

  /**
   * Moves the grenade and makes it explode
   */
  public class MoveAndTrigger extends MoveAndConsume {

    public MoveAndTrigger() {
      super(new WillExplodeVisitor(Grenade.this));
    }
  }

  /**
   * Action class representing the explosion of the grenade
   */
  public class Explode implements Action {

    @Override
    public void execute(World world) {
      Grenade.this.willExplode = false;
      Grenade.this.exploding = true;
      world.visitVisibleAnimatesFrom(Grenade.this, Grenade.RANGE, new ParticipantVisitor() {
        @Override
        public void visit(Participant participant) {
          participant.damage(DAMAGE);
        }
      });
    }
  }
}
