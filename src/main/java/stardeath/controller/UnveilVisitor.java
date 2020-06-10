package stardeath.controller;

import stardeath.participants.ParticipantAdapter;
import stardeath.participants.player.Player;

public class UnveilVisitor extends ParticipantAdapter {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(player.new UnveilAction());
  }
}
