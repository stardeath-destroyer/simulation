package stardeath.animates.participants.entities;

import stardeath.animates.Animate;
import stardeath.animates.Action;
import stardeath.animates.participants.Faction;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.visitors.DefaultAnimateVisitor;
import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.World;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.visitors.DefaultTileVisitor;
import stardeath.world.visitors.NoOpTileVisitor;
import stardeath.world.visitors.TileVisitor;

public class Player extends Soldier {

  public static final int MAX_HEALTH_POINTS = 600;
  private static final int DEFAULT_VISIBILITY_RANGE = 8;

  public Player(Vector position) {
    super(position, Faction.Rebels, MAX_HEALTH_POINTS, DEFAULT_VISIBILITY_RANGE);
  }

  @Override
  public boolean isVisible() {
    return true;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

  public static class MaskAllParticipants implements Action {

    @Override
    public void execute(World world) {
      world.visitAnimates(new DefaultAnimateVisitor(Animate::hide));
    }
  }

  public class ShowCloseParticipants implements Action {

    @Override
    public void execute(World world) {
      world.visitVisibleAnimatesFrom(Player.this, new DefaultAnimateVisitor(Animate::show));
    }
  }

  public class UnveilAction implements Action {

    @Override
    public void execute(World world) {
      world.visitVisibleTilesFrom(Player.this, new DefaultTileVisitor(Tile::unveil));
    }
  }

  public class Interact implements Action {

    @Override
    public void execute(World world) {
      TileVisitor visitor = new NoOpTileVisitor() {
        @Override
        public void visitTile(DownwardElevator elevator) {
          world.moveDown();
        }

        @Override
        public void visitTile(UpwardElevator elevator) {
          world.moveUp();
        }

        @Override
        public void visitTile(Terminal terminal) {
          terminal.destroy();
        }
      };

      // TODO : Use a Visitor on a tile at a specific index instead.
      world.tileAt(getPosition()).ifPresent(tile -> tile.accept(visitor));
    }
  }
}
