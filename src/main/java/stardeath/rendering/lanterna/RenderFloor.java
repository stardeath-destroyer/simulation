package stardeath.rendering.lanterna;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import stardeath.world.Tile;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public class RenderFloor implements TileVisitor {

  private final Screen screen;


  public RenderFloor(Screen screen) {
    this.screen = screen;
  }

  private void setGrid(Tile tile, char symbol) {
    if (tile.isDiscovered()) {
      screen.setCharacter(tile.getX(), tile.getY(), new TextCharacter(symbol));
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
