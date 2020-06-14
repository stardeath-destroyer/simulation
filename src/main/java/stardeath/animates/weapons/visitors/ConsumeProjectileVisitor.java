package stardeath.animates.weapons.visitors;

import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.entities.LaserBeam;

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
  public void visitParticipant(FlameTrooper trooper) {
    hit(trooper);
    consumed = true;
  }

  @Override
  public void visitParticipant(Trooper trooper) {
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
  public void visitProjectile(LaserBeam projectile) {
    // Standard projectiles do not collide.
  }
}
