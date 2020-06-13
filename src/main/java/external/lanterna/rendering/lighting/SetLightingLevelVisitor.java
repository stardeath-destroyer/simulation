package external.lanterna.rendering.lighting;

import java.util.function.Function;
import stardeath.world.Vector;
import stardeath.world.visitors.DefaultTileVisitor;

/**
 * An implementation of a {@link DefaultTileVisitor} that applies a certain intensity of light and
 * saves it in an existing {@link LightingLevel} matrix. This will override the previously {@link
 * LightingLevel}.
 */
public class SetLightingLevelVisitor extends DefaultTileVisitor {

  public SetLightingLevelVisitor(
      Function<Vector, LightingLevel> intensity,
      LightingLevel[][] existing) {
    super(tile -> existing
        [tile.getPosition().getX()]
        [tile.getPosition().getY()] = intensity.apply(tile.getPosition())
    );
  }
}
