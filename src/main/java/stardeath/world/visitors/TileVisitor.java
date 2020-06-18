package stardeath.world.visitors;

import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

/**
 * Base Tile visitor
 */
public interface TileVisitor {

  /**
   * Visits a elevator that only goes downwards
   * @param elevator The elevator to visit
   */
  void visitTile(DownwardElevator elevator);

  /**
   * Visits a hole
   * @param hole The hole to visit
   */
  void visitTile(Hole hole);

  /**
   * Visits a regular tile
   * @param regular The normal tile to visit
   */
  void visitTile(Regular regular);

  /**
   * Visits a starting tile
   * @param start The starting tile to visit
   */
  void visitTile(Start start);

  /**
   * Visits a terminal
   * @param terminal The terminal to visit
   */
  void visitTile(Terminal terminal);

  /**
   * Visits an elevator that only goes upwards
   * @param elevator The elevator to visit
   */
  void visitTile(UpwardElevator elevator);

  /**
   * Visits a wall
   * @param wall The wall to visit
   */
  void visitTile(Wall wall);
}
