package stardeath.controller.visitors;

import stardeath.animates.Animate;
import stardeath.animates.Action;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;
import stardeath.world.World;

public class ExecuteActions implements AnimateVisitor {

  private final World world;

  public ExecuteActions(World world) {
    this.world = world;
  }

  private void visit(Animate animate) {
    for (Action action : animate.getActions()) {
      action.execute(world);
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
  public void visitParticipant(FlameTrooper trooper) {
    visit(trooper);
  }

  @Override
  public void visitParticipant(Trooper trooper) {
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
  public void visitProjectile(LaserBeam projectile) {
    visit(projectile);
  }

  @Override
  public void visitProjectile(Grenade grenade) {
    visit(grenade);
  }
}
