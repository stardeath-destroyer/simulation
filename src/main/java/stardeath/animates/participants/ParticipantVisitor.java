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

public abstract class ParticipantVisitor implements AnimateVisitor {

  public abstract void visit(Participant participant);

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
    // Ignored.
  }

  @Override
  public void visitProjectile(Grenade grenade) {
    // Ignored.
  }
}
