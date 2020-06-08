package stardeath.world;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  public void visit(TileVisitor visitor) {
    for (Tile tile : tiles) {
      tile.accept(visitor);
    }
  }
}
