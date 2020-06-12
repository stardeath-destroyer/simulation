package stardeath.animates.participants.entities;

import stardeath.animates.Animate;
import stardeath.animates.actions.Action;
import stardeath.animates.participants.Faction;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.visitors.DefaultAnimateVisitor;
import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.World;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.visibility.RayCasting;
import stardeath.world.visitors.NoOpTileVisitor;
import stardeath.world.visitors.TileVisitor;

public class Player extends Soldier {

  private static final int DEFAULT_VISIBILITY_RANGE = 13;

  private int visibilityRange;

  public Player(Vector position) {
    super(position, Faction.Rebels, 100);
    this.visibilityRange = DEFAULT_VISIBILITY_RANGE;
  }

  public int getVisibilityRange() {
    return this.visibilityRange;
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
      world.current().visitAnimates(new DefaultAnimateVisitor(Animate::hide));
    }
  }

  public class ShowCloseParticipants implements Action {

    @Override
    public void execute(World world) {
      RayCasting.compute(Player.this, visibilityRange,
          (p) -> world.current().tileAt(p).map(Tile::isOpaque).orElse(false),
          (p) -> world.current().participantAt(p).ifPresent(Animate::show));
    }
  }

  public class UnveilAction implements Action {

    @Override
    public void execute(World world) {
      RayCasting.compute(Player.this, visibilityRange,
          (p) -> world.current().tileAt(p).map(Tile::isOpaque).orElse(false),
          (p) -> world.current().tileAt(p).ifPresent(Tile::unveil));
    }
  }

  public class TakeLift implements Action {

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
      };

      // TODO : Use a Visitor on a tile at a specific index instead.
      world.current().tileAt(getPosition()).ifPresent(tile -> tile.accept(visitor));
    }
  }
}
