package stardeath.world.visitors;

import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Wall;

public abstract class NoOpTileVisitor implements TileVisitor {

  @Override
  public void visitTile(Armory armory) {

  }

  @Override
  public void visitTile(Dump dump) {

  }

  @Override
  public void visitTile(Elevator elevator) {

  }

  @Override
  public void visitTile(Hole hole) {

  }

  @Override
  public void visitTile(Regular regular) {

  }

  @Override
  public void visitTile(Start start) {

  }

  @Override
  public void visitTile(Wall wall) {

  }
}
