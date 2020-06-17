package external.lanterna.rendering.visitor;

import stardeath.animates.participants.entities.Player;
import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.world.Vector;

/**
 * An implementation of a {@link NoOpAnimateVisitor} that simply finds the player in the level
 * (assuming there is even one).
 */
public class FindPlayerOrDefaultVisitor extends NoOpAnimateVisitor {

  private Player player = new Player(Vector.EMPTY);

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Player player) {
    this.player = player;
  }

  /**
   * Returns the found player or a default one.
   * @return The current player or a default one.
   */
  public Player getPlayer() {
    return player;
  }
}
