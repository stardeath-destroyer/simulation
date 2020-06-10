package stardeath.controller;

import stardeath.animates.NoOpAnimateVisitor;
import stardeath.participants.player.Player;

public class UnveilVisitor extends NoOpAnimateVisitor {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(player.new UnveilAction());
  }
}
