package stardeath.controller.visitors;

import stardeath.animates.participants.entities.Player;
import stardeath.world.tiles.Terminal;
import stardeath.visitors.NoOpEntityVisitor;

public class GameStateVisitor extends NoOpEntityVisitor {

  private boolean isPlayerDead = true;
  private int onlineTerminals = 0;

  @Override
  public void visitParticipant(Player player) {
    // Do we have at least one player who should not be removed ?
    if (!player.shouldRemove()) {
      isPlayerDead = false;
    }
  }

  @Override
  public void visitTile(Terminal terminal) {
    if (terminal.isOnline()) {
      ++onlineTerminals;
    }
  }

  public boolean isPlayerDead() {
    return isPlayerDead;
  }

  public int onlineTerminals() {
    return onlineTerminals;
  }
}
