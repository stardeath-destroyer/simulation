package stardeath.controller.visitors;

import stardeath.animates.participants.entities.Player;
import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.world.World;
import stardeath.world.tiles.Hole;
import stardeath.world.visitors.NoOpTileVisitor;

/**
 * This visitor is used to make things fall through Hole Tiles
 */
public class FallThroughHoles extends NoOpTileVisitor {

  private static final float FALL_DAMAGE_RATIO = 0.15f;

  private final World world;

  public FallThroughHoles(World world) {
    this.world = world;
  }

  @Override
  public void visitTile(Hole hole) {
    world.visitAnimateAt(hole.getPosition(), new NoOpAnimateVisitor() {

      @Override
      public void visitParticipant(Player player) {
        player.damage((int) (FALL_DAMAGE_RATIO * player.getHealthPoints()));
        world.moveDown();
      }
    });
  }
}
