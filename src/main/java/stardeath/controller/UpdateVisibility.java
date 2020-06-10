package stardeath.controller;

import stardeath.animates.NoOpAnimateVisitor;
import stardeath.participants.actions.Action;
import stardeath.participants.player.Player;

public class UpdateVisibility extends NoOpAnimateVisitor {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(Action.of(
        new Player.MaskAllParticipants(),
        player.new ShowCloseParticipants()
    ));
  }
}
