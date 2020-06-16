package stardeath.controller.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.world.Tile;
import stardeath.world.tiles.Hole;
import stardeath.world.visitors.NoOpTileVisitor;

public class TileDetectionVisitor extends NoOpTileVisitor {
  List<Tile> avoid = new ArrayList<>();

  @Override
  public void visitTile(Hole hole) {
    avoid.add(hole);
  }

  public List<Tile> tilesToAvoid() {
    return Collections.unmodifiableList(avoid);
  }
}
