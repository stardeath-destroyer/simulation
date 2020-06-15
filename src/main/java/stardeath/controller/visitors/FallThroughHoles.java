package stardeath.controller.visitors;

import stardeath.animates.participants.entities.Player;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.world.World;
import stardeath.world.tiles.Hole;
import stardeath.world.visitors.NoOpTileVisitor;

public class FallThroughHoles extends NoOpTileVisitor {

  private final World world;

  public FallThroughHoles(World world) {
    this.world = world;
  }

  @Override
  public void visitTile(Hole hole) {
    world.participantAt(hole.getPosition()).ifPresent(animate -> {
      AnimateVisitor visitor = new NoOpAnimateVisitor() {
        @Override
        public void visitParticipant(Player player) {
          world.moveDown();
        }
      };
      animate.accept(visitor);
    });
  }
}