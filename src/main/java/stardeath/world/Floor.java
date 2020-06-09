package stardeath.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import stardeath.world.tiles.Start;

public final class Floor {

  private Floor previous;
  private Floor next;
  private final List<Tile> tiles;

  public Floor(Tile... tiles) {
    this.tiles = Stream.of(tiles).collect(Collectors.toList());
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
