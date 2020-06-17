package stardeath.animates.weapons.visitors;

import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.world.tiles.Wall;

/**
 * This visitor is used to make grenades explode on certain Entities
 */
public class WillExplodeVisitor extends ConsumableVisitor {

  private final Grenade grenade;

  private void consume() {
    this.consumed = true;
    grenade.trigger();
  }

  /**
   * {@inheritDoc}
   */
  public WillExplodeVisitor(Grenade grenade) {
    this.grenade = grenade;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Player player) {
    consume();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(JumpTrooper trooper) {
    consume();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(FlameTrooper trooper) {
    consume();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Trooper trooper) {
    consume();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Soldier soldier) {
    consume();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Wall wall) {
    consume();
  }
}
