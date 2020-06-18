package stardeath.animates.participants;

import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;

/**
 * Main participant visitor
 */
public abstract class ParticipantVisitor implements AnimateVisitor {

  public abstract void visit(Participant participant);

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Player player) {
    visit(player);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(JumpTrooper trooper) {
    visit(trooper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(FlameTrooper trooper) {
    visit(trooper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Trooper trooper) {
    visit(trooper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Soldier soldier) {
    visit(soldier);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Wookie wookie) {
    visit(wookie);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitProjectile(LaserBeam projectile) {
    // Ignored.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitProjectile(Grenade grenade) {
    // Ignored.
  }
}
