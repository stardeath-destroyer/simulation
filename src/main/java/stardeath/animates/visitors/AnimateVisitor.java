package stardeath.animates.visitors;

import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;

/**
 * Main interface to visit animates
 */
public interface AnimateVisitor {

  /**
   * Visits the player
   * @param player The player to visit
   */
  void visitParticipant(Player player);

  /**
   * Visits a JumpTrooper
   * @param trooper The JumpTrooper to visit
   */
  void visitParticipant(JumpTrooper trooper);

  /**
   * Visits a FlameTrooper
   * @param trooper The FlameTrooper to visit
   */
  void visitParticipant(FlameTrooper trooper);

  /**
   * Visits a Trooper
   * @param trooper The Trooper to visit
   */
  void visitParticipant(Trooper trooper);

  /**
   * Visits a Soldier
   * @param soldier The Soldier to visit
   */
  void visitParticipant(Soldier soldier);

  /**
   * Visits a Wookie
   * @param wookie The Wookie to visit
   */
  void visitParticipant(Wookie wookie);

  /**
   * Visits a projectile
   * @param projectile The projectile to visit
   */
  void visitProjectile(LaserBeam projectile);

  /**
   * Visits a grenade
   * @param grenade The grenade to visit
   */
  void visitProjectile(Grenade grenade);
}
