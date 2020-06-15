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

  @Override
  public void visitParticipant(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }
}
