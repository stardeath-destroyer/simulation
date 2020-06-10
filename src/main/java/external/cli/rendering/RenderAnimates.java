package external.cli.rendering;

import stardeath.animates.Animate;
import stardeath.animates.AnimateVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;
import stardeath.participants.weapons.Projectile;

public class RenderAnimates implements AnimateVisitor {

  private final char[][] buffer;

  public RenderAnimates(char[][] buffer) {
    this.buffer = buffer;
  }

  private void setGrid(Animate animate, char symbol) {
    if (animate.isVisible()) {
      buffer[animate.getY()][animate.getX()] = symbol;
    }
  }

  @Override
  public void visitParticipant(Player player) {
    setGrid(player, 'P');
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
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
  public void visitProjectile(Projectile projectile) {
    setGrid(projectile, '*');
  }
}
