package stardeath.animates.actions;

import stardeath.animates.Animate;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.Projectile;
import stardeath.world.Floor;

public class ExecuteActions implements AnimateVisitor {

  private Floor floor;

  public ExecuteActions(Floor floor) {
    this.floor = floor;
  }

  private void visit(Animate animate) {
    for (Action action : animate.getActions()) {
      action.execute(floor);
    }
    animate.clearActions();
  }

  @Override
  public void visitParticipant(Player player) {
    visit(player);
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    visit(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    visit(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    visit(wookie);
  }

  @Override
  public void visitProjectile(Projectile projectile) {
    visit(projectile);
  }
}
