package stardeath.world.visitors;

import java.util.function.Consumer;
import stardeath.world.Tile;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

/**
 * Default visitor for tiles. All tiles will be applied the same Consumer
 */
public class DefaultTileVisitor implements TileVisitor {

  private Consumer<Tile> consumer;

  public DefaultTileVisitor(Consumer<Tile> consumer) {
    this.consumer = consumer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(DownwardElevator elevator) {
    consumer.accept(elevator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Hole hole) {
    consumer.accept(hole);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Regular regular) {
    consumer.accept(regular);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Start start) {
    consumer.accept(start);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Terminal terminal) {
    consumer.accept(terminal);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(UpwardElevator elevator) {
    consumer.accept(elevator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Wall wall) {
    consumer.accept(wall);
  }
}
