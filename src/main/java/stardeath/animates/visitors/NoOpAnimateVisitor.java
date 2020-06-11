package stardeath.animates.visitors;

import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.Projectile;

public class NoOpAnimateVisitor implements AnimateVisitor {

  @Override
  public void visitParticipant(Player player) {

  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {

  }

  @Override
  public void visitParticipant(Soldier soldier) {

  }

  @Override
  public void visitParticipant(Wookie wookie) {

  }

  @Override
  public void visitProjectile(Projectile projectile) {

  }
}
