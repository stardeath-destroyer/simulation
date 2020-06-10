package stardeath.animates;

import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;
import stardeath.participants.weapons.Projectile;

public interface AnimateVisitor {

  void visitParticipant(Player player);

  void visitParticipant(JumpTrooper trooper);

  void visitParticipant(Soldier soldier);

  void visitParticipant(Wookie wookie);

  void visitProjectile(Projectile projectile);
}
