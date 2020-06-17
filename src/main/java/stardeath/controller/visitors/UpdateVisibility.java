package stardeath.controller.visitors;

import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.animates.Action;
import stardeath.animates.participants.entities.Player;

/**
 * This visitor is used to hide all animates that are not visible by the player
 */
public class UpdateVisibility extends NoOpAnimateVisitor {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(Action.of(
        new Player.MaskAllParticipants(),
        player.new ShowCloseParticipants()
    ));
  }
}
