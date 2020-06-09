package stardeath.controller;

import stardeath.participants.ParticipantAdapter;
import stardeath.participants.player.Player;
import stardeath.world.Floor;

public class UnveilVisitor extends ParticipantAdapter {

  private final Floor level;

  public UnveilVisitor(Floor level) {
    this.level = level;
  }

  @Override
  public void visitParticipant(Player player) {
    player.addAction(player.new UnveilAction(level));
  }
}
