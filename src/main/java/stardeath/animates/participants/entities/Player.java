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

/**
 * Represents the player
 */
public class Player extends Soldier {

  public static final int MAX_HEALTH_POINTS = 600;
  private static final int DEFAULT_VISIBILITY_RANGE = 8;

  public Player(Vector position) {
    super(position, Faction.Rebels, MAX_HEALTH_POINTS, DEFAULT_VISIBILITY_RANGE);
  }

  /**
   * Always returns true, since the player must always be visible
   */
  @Override
  public boolean isVisible() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

  /**
   * This action will mask all participant to the player
   */
  public static class MaskAllParticipants implements Action {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(World world) {
      world.visitAnimates(new DefaultAnimateVisitor(Animate::hide));
    }
  }

  /**
   * This action will reveal only the participants that are close enough to the player
   */
  public class ShowCloseParticipants implements Action {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(World world) {
      world.visitVisibleAnimatesFrom(Player.this, new DefaultAnimateVisitor(Animate::show));
    }
  }

  /**
   * This action will reveal tiles that the player hasn't seen yet
   */
  public class UnveilAction implements Action {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(World world) {
      world.visitVisibleTilesFrom(Player.this, new DefaultTileVisitor(Tile::unveil));
    }
  }

  /**
   * This class allows the player to interact with certain tiles, like elevator for example
   */
  public class Interact implements Action {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(World world) {
      TileVisitor visitor = new NoOpTileVisitor() {
        @Override
        public void visitTile(DownwardElevator elevator) {
          world.moveDown();
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void visitTile(UpwardElevator elevator) {
          world.moveUp();
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void visitTile(Terminal terminal) {
          terminal.destroy();
        }
      };

      world.visitTileAt(getPosition(), visitor);
    }
  }
}
