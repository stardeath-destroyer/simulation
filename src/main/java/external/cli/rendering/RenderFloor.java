package external.cli.rendering;

import stardeath.world.Tile;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
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
  public void visitTile(Regular regular) {
    setGrid(regular, '.');
  }

  @Override
  public void visitTile(Elevator elevator) {
    setGrid(elevator, '_');
  }

  @Override
  public void visitTile(Hole hole) {
    setGrid(hole, ' ');
  }

  @Override
  public void visitTile(Wall wall) {
    setGrid(wall, 'x');
  }
}
