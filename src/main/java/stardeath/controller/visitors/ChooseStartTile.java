package stardeath.controller.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import stardeath.world.tiles.Start;
import stardeath.world.visitors.NoOpTileVisitor;

public class ChooseStartTile extends NoOpTileVisitor {

  private final List<Start> candidates = new ArrayList<>();

  public ChooseStartTile() {
    super();
  }

  @Override
  public void visitTile(Start start) {
    candidates.add(start);
  }

  public Optional<Start> pickRandom() {
    Collections.shuffle(candidates);
    return candidates.stream().findFirst();
  }
}
