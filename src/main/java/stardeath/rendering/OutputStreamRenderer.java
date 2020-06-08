package stardeath.rendering;

import java.io.IOException;
import java.io.OutputStream;
import stardeath.world.Floor;
import stardeath.world.Tile;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public class OutputStreamRenderer implements Renderer {

  private final OutputStream stream;

  public OutputStreamRenderer(OutputStream stream) {
    this.stream = stream;
  }

  @Override
  public void render(Floor floor) {
    int maxX = 0, maxY = 0;
    for (Tile tile : floor.getTiles()) {
      maxX = Math.max(maxX, tile.getX() + 1);
      maxY = Math.max(maxY, tile.getY() + 1);
    }

    char[][] elements = new char[maxY][maxX];

    floor.visit(new TileVisitor() {

      private void setGrid(Tile tile, char symbol) {
        elements[tile.getY()][tile.getX()] = symbol;
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
    });

    try {
      for (char[] line : elements) {
        for (char tile : line) {
          stream.write(tile);
        }
        stream.write('\n');
      }
    } catch (IOException any) {
      // Too bad.
    }
  }
}
