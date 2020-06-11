package stardeath.controller.visitors;

import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.animates.participants.entities.Player;

public class UnveilVisitor extends NoOpAnimateVisitor {

  @Override
  public void visitParticipant(Player player) {
    player.addAction(player.new UnveilAction());
  }
}
