package stardeath.controller.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import stardeath.world.tiles.Start;
import stardeath.world.visitors.NoOpTileVisitor;

/**
 * This visitor is used to find the possible starting tiles
 */
public class ChooseStartTile extends NoOpTileVisitor {

  private final List<Start> candidates = new ArrayList<>();

  public ChooseStartTile() {
    super();
  }

  @Override
  public void visitTile(Start start) {
    candidates.add(start);
  }

  /**
   * After we have visited all the tiles, gets a random start tile
   * @return A Start tile or empty if no starting tiles were found
   */
  public Optional<Start> pickRandom() {
    Collections.shuffle(candidates);
    return candidates.stream().findFirst();
  }
}
