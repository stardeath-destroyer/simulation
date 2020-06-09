package stardeath.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import stardeath.world.tiles.Start;

public final class Floor {

  private final List<Tile> tiles;
  private Floor previous;
  private Floor next;
  private int width;
  private int height;

  public Floor(Tile... tiles) {
    this.tiles = Stream.of(tiles).collect(Collectors.toList());
    this.tiles.forEach(tile -> {
      width = Math.max(tile.getX(), width);
      height = Math.max(tile.getY(), height);
    });
  }

  public final int getWidth() {
    return width;
  }

  public final int getHeight() {
    return height;
  }

  public final List<Tile> getTiles() {
    return Collections.unmodifiableList(tiles);
  }

  public final List<Start> getStartTiles() {
    List<Start> tiles = new ArrayList<>();
    visit(new TileVisitorAdapter() {
      @Override
      public void visitTile(Start start) {
        tiles.add(start);
      }
    });
    return tiles;
  }

  public void visit(TileVisitor visitor) {
    for (Tile tile : tiles) {
      tile.accept(visitor);
    }
  }
}
