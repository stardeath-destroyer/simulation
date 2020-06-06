package stardeath.world;

import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public interface TileVisitor {

  void visitTile(Regular regular);

  void visitTile(Elevator elevator);

  void visitTile(Wall wall);
}
