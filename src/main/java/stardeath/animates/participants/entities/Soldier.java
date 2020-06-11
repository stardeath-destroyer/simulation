package stardeath.animates.participants.entities;

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

    private final ProjectileDirection direction;
    private final int speed;

    public Fire(ProjectileDirection direction, int speed) {
      this.direction = direction;
      this.speed = speed;
    }

    @Override
    public void execute(Floor level) {
      // Make the projectile leave the player before adding it, to make sure the Player does not
      // shoot himself by mistake.
      int startX = direction.getSteps().get(0).getX() + getX();
      int startY = direction.getSteps().get(0).getY() + getY();

      // Add the projectile.
      level.addAnimate(new Projectile(startX, startY, 10, direction, speed));
    }
  }
}
