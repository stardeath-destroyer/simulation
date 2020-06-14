package external.lanterna.rendering.lighting;

import java.util.function.Function;
import stardeath.world.Vector;

/**
 * A simple {@link Function} that, given an origin {@link Vector} and a certain radial range, will
 * determine the {@link LightingLevel} to apply for a certain position {@link Vector} with a linear
 * policy.
 */
public class LinearDistanceIntensity implements Function<Vector, LightingLevel> {

  private final Vector origin;
  private final int range;

  public LinearDistanceIntensity(Vector origin, int range) {
    this.origin = origin;
    this.range = range;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LightingLevel apply(Vector vector) {
    int distance = origin.distanceTo(vector);

    if (distance > 1.0 * range) {
      return LightingLevel.Dark;
    } else if (distance > 0.75 * range) {
      return LightingLevel.Medium;
    } else if (distance > 0.5 * range) {
      return LightingLevel.Bright;
    } else {
      return LightingLevel.Brightest;
    }
  }
}
