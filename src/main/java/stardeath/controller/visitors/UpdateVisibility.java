package stardeath.controller.visitors;

import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.animates.actions.Action;
import stardeath.animates.participants.entities.Player;

public class UpdateVisibility extends NoOpAnimateVisitor {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(Action.of(
        new Player.MaskAllParticipants(),
        player.new ShowCloseParticipants()
    ));
  }
}
