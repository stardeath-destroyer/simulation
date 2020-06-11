package stardeath.animates.visitors;

import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.Projectile;

public interface AnimateVisitor {

  void visitParticipant(Player player);

  void visitParticipant(JumpTrooper trooper);

  void visitParticipant(Soldier soldier);

  void visitParticipant(Wookie wookie);

  void visitProjectile(Projectile projectile);
}
