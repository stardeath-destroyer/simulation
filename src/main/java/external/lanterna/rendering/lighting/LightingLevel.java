package external.lanterna.rendering.lighting;

public enum LightingLevel {
  Darkest,
  Dark,
  Medium,
  Bright,
  Brightest;

  /**
   * Sums two {@link LightingLevel} in a linear fashion, considering that the darkest lighting value
   * is 0 and the brightest is 4. Because we start at 0, adding a {@link LightingLevel#Darkest}
   * level to any other level will not change the original level.
   *
   * @param other The {@link LightingLevel} to add.
   * @return The sum of the {@link LightingLevel}, bounded at {@link LightingLevel#Brightest}.
   */
  public LightingLevel plus(LightingLevel other) {
    int ordinal = Math.min(values().length - 1, ordinal() + other.ordinal());
    return LightingLevel.values()[ordinal];
  }
}
