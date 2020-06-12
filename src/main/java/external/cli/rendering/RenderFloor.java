package external.cli.rendering;

import stardeath.world.Tile;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.visitors.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Wall;

public class RenderFloor implements TileVisitor {

  private final char[][] buffer;

  public RenderFloor(char[][] buffer) {
    this.buffer = buffer;
  }

  private void setGrid(Tile tile, char symbol) {
    if (tile.isDiscovered()) {
      buffer[tile.getY()][tile.getX()] = symbol;
    }
  }

  @Override
  public void visitTile(Armory armory) {
    setGrid(armory, 'A');
  }

  @Override
  public void visitTile(Dump dump) {
    setGrid(dump, 'D');
  }

  @Override
  public void visitTile(DownwardElevator elevator) {
    setGrid(elevator, 'v');
  }

  @Override
  public void visitTile(Regular regular) {
    setGrid(regular, '.');
  }

  @Override
  public void visitTile(Hole hole) {
    setGrid(hole, ' ');
  }

  @Override
  public void visitTile(Start start) {
    // Ignored.
  }

  @Override
  public void visitTile(UpwardElevator elevator) {
    setGrid(elevator, '^');
  }

  @Override
  public void visitTile(Wall wall) {
    setGrid(wall, 'x');
  }
}
