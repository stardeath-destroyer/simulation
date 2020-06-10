package stardeath.participants.actions;

import stardeath.animates.Animate;
import stardeath.animates.AnimateVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;
import stardeath.participants.weapons.Projectile;
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
