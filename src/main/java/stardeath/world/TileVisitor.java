package stardeath.world;

import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.HorizontalWall;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.VerticalWall;
import stardeath.world.tiles.Wall;

public interface TileVisitor {

  void visitTile(Armory armory);
  void visitTile(Dump dump);
  void visitTile(Regular regular);
  void visitTile(Elevator elevator);
  void visitTile(Hole hole);
  void visitTile(VerticalWall wall);
  void visitTile(HorizontalWall wall);
}
