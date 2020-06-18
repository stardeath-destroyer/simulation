package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.Action;
import stardeath.animates.participants.Faction;
import stardeath.animates.weapons.Projectile;
import stardeath.world.Vector;
import stardeath.world.World;

/**
 * A basic soldier. Can shoot projectiles
 */
public class Soldier extends Human {

  protected Soldier(Vector position, Faction faction, int hp, int visibilityRange) {
    super(position, faction, hp, visibilityRange);
  }

  public Soldier(Vector position) {
    super(position, Faction.Rebels, 70, 7);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

  /**
   * This action allows the soldier to shoot a projectile
   */
  public class Fire implements Action {

    private final Projectile projectile;

    public Fire(Projectile projectile) {
     this.projectile = projectile;
    }

    @Override
    public void execute(World world) {
      // Make the projectile leave the player before adding it, to make sure the Player does not
      // shoot himself by mistake.
      projectile.setPosition(projectile.getDirection()
          .getVector()
          .add(getPosition()));

      // Add the projectile.
      world.addAnimate(projectile);
    }
  }
}
