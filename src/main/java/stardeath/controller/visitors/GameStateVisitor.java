package stardeath.controller.visitors;

import stardeath.animates.participants.entities.Player;
import stardeath.world.tiles.Terminal;
import stardeath.world.visitors.NoOpEntityVisitor;

public class GameStateVisitor extends NoOpEntityVisitor {
  boolean isPlayerDead = false;
  int onlineTerminals = 0;

  @Override
  public void visitParticipant(Player player) {
    isPlayerDead = player.shouldRemove();
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
