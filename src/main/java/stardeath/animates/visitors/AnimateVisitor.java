package stardeath.animates.visitors;

import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.entities.LaserBeam;

public interface AnimateVisitor {

  void visitParticipant(Player player);

  void visitParticipant(JumpTrooper trooper);

  void visitParticipant(FlameTrooper trooper);

  void visitParticipant(Trooper trooper);

  void visitParticipant(Soldier soldier);

  void visitParticipant(Wookie wookie);

  void visitProjectile(LaserBeam projectile);
}
