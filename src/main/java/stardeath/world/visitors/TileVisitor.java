package stardeath.world.visitors;

import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

public interface TileVisitor {

  void visitTile(DownwardElevator elevator);

  void visitTile(Hole hole);

  void visitTile(Regular regular);

  void visitTile(Start start);

  void visitTile(Terminal terminal);

  void visitTile(UpwardElevator elevator);

  void visitTile(Wall wall);
}
