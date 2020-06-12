package stardeath.animates.participants.entities;

import java.util.function.BiFunction;
import java.util.function.Function;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.actions.Action;
import stardeath.animates.participants.Faction;
import stardeath.animates.weapons.Projectile;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.world.Floor;

public class Soldier extends Human {

  protected Soldier(int x, int y, Faction faction, int hp) {
    super(x, y, faction, hp);
  }

  public Soldier(int x, int y) {
    super(x, y, Faction.Rebels, 70);
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
    public void execute(Floor level) {
      // Make the projectile leave the player before adding it, to make sure the Player does not
      // shoot himself by mistake.
      int startX = projectile.getDirection().getSteps().get(0).getX() + getX();
      int startY = projectile.getDirection().getSteps().get(0).getY() + getY();

      projectile.setPosition(startX, startY);

      // Add the projectile.
      level.addAnimate(projectile);
    }
  }
}
