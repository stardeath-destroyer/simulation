package external.cli.rendering;

import stardeath.animates.Animate;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;

public class RenderAnimates implements AnimateVisitor {

  private final char[][] buffer;

  public RenderAnimates(char[][] buffer) {
    this.buffer = buffer;
  }

  private void setGrid(Animate animate, char symbol) {
    if (animate.isVisible()) {
      buffer[animate.getPosition().getY()][animate.getPosition().getX()] = symbol;
    }
  }

  @Override
  public void visitParticipant(Player player) {
    setGrid(player, 'P');
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    setGrid(trooper, 'J');
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    setGrid(trooper, 'F');
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    setGrid(trooper, 'T');
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    setGrid(soldier, 'S');
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    setGrid(wookie, 'W');
  }

  @Override
  public void visitProjectile(LaserBeam projectile) {
    setGrid(projectile, '*');
  }

  @Override
  public void visitProjectile(Grenade grenade) {
    setGrid(grenade, 'ó');
  }
}
