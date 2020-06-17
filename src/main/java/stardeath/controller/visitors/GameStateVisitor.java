package stardeath.controller.visitors;

import stardeath.animates.participants.entities.Player;
import stardeath.world.tiles.Terminal;
import stardeath.visitors.NoOpEntityVisitor;

/**
 * This visitor is used to check the current state of the game
 */
public class GameStateVisitor extends NoOpEntityVisitor {

  private boolean isPlayerDead = true;
  private int onlineTerminals = 0;

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Player player) {
    // Do we have at least one player who should not be removed ?
    if (!player.shouldRemove()) {
      isPlayerDead = false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Terminal terminal) {
    if (terminal.isOnline()) {
      ++onlineTerminals;
    }
  }

  /**
   * Returns true if the player is dead
   * @return True if the player is dead
   */
  public boolean isPlayerDead() {
    return isPlayerDead;
  }

  /**
   * Returns the number of terminals that are still online
   * @return The number of terminals that are still online
   */
  public int onlineTerminals() {
    return onlineTerminals;
  }
}
