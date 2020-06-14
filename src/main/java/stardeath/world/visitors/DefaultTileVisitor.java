package stardeath.world.visitors;

import java.util.function.Consumer;
import stardeath.world.Tile;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

public class DefaultTileVisitor implements TileVisitor {

  private Consumer<Tile> consumer;

  public DefaultTileVisitor(Consumer<Tile> consumer) {
    this.consumer = consumer;
  }

  @Override
  public void visitTile(Armory armory) {
    consumer.accept(armory);
  }

  @Override
  public void visitTile(Dump dump) {
    consumer.accept(dump);
  }

  @Override
  public void visitTile(DownwardElevator elevator) {
    consumer.accept(elevator);
  }

  @Override
  public void visitTile(Hole hole) {
    consumer.accept(hole);
  }

  @Override
  public void visitTile(Regular regular) {
    consumer.accept(regular);
  }

  @Override
  public void visitTile(Start start) {
    consumer.accept(start);
  }

  @Override
  public void visitTile(Terminal terminal) {
    consumer.accept(terminal);
  }

  @Override
  public void visitTile(UpwardElevator elevator) {
    consumer.accept(elevator);
  }

  @Override
  public void visitTile(Wall wall) {
    consumer.accept(wall);
  }
}
