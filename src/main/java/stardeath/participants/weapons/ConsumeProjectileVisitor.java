package stardeath.participants.weapons;

import stardeath.animates.AnimateVisitor;
import stardeath.participants.Participant;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public class ConsumeProjectileVisitor implements AnimateVisitor {

  private boolean consumed;
  private final int force;

  public ConsumeProjectileVisitor(int force) {
    this.force = force;
  }

  public boolean isConsumed() {
    return consumed;
  }

  private void hit(Participant participant) {
    participant.damage(this.force);
  }

  @Override
  public void visitParticipant(Player player) {
    hit(player);
    consumed = true;
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    hit(trooper);
    consumed = true;
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    hit(soldier);
    consumed = true;
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    hit(wookie);
    consumed = true;
  }

  @Override
  public void visitProjectile(Projectile projectile) {
    // Standard projectiles do not collide.
  }
}
