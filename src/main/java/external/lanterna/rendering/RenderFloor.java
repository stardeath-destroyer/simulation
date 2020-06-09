package external.lanterna.rendering;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor.ANSI;
import stardeath.world.Tile;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Wall;

public class RenderFloor implements TileVisitor {

  private final TextCharacter[][] buffer;

  public RenderFloor(TextCharacter[][] buffer) {
    this.buffer = buffer;
  }

  private void setGrid(Tile tile, TextCharacter c) {
    if (tile.isDiscovered()) {
      buffer[tile.getX()][tile.getY()] = c;
    }
  }

  @Override
  public void visitTile(Armory armory) {
    setGrid(armory, new TextCharacter('A'));
  }

  @Override
  public void visitTile(Dump dump) {
    setGrid(dump, new TextCharacter('D'));
  }

  @Override
  public void visitTile(Regular regular) {
    setGrid(regular, new TextCharacter('.'));
  }

  @Override
  public void visitTile(Elevator elevator) {
    setGrid(elevator, new TextCharacter('_'));
  }

  @Override
  public void visitTile(Hole hole) {
    setGrid(hole, new TextCharacter(' '));
  }

  @Override
  public void visitTile(Start start) {
    // Ignored.
  }

  @Override
  public void visitTile(Wall wall) {
    setGrid(wall, new TextCharacter(' ', ANSI.DEFAULT, ANSI.WHITE));
  }
}
