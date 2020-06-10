package stardeath.controller;

import stardeath.participants.ParticipantAdapter;
import stardeath.participants.actions.Action;
import stardeath.participants.player.Player;

public class UpdateVisibility extends ParticipantAdapter {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(Action.of(
        new Player.MaskAllParticipants(),
        player.new ShowCloseParticipants()
    ));
  }
}
