package stardeath.controller.visitors;

import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.animates.participants.entities.Player;

/**
 * Is used to unveil tiles from the player's perspective
 */
public class UnveilVisitor extends NoOpAnimateVisitor {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(player.new UnveilAction());
  }
}
