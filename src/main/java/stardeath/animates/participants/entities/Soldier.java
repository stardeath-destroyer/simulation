package stardeath.animates.participants.entities;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.actions.Action;
import stardeath.animates.participants.Faction;
import stardeath.animates.weapons.Projectile;
import stardeath.world.Vector;
import stardeath.world.World;

public class Soldier extends Human {

  protected Soldier(Vector position, Faction faction, int hp) {
    super(position, faction, hp);
  }

  public Soldier(Vector position) {
    super(position, Faction.Rebels, 70);
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

  @Override
  public int getAttackRange() {
    return 4;
  }

  @Override
  public int getAttackDamage() {
    return 150;
  }

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
          .getSteps().get(0)
          .add(getPosition()));

      // Add the projectile.
      world.current().addAnimate(projectile);
    }
  }
}
