package external.lanterna.rendering.texturing;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import external.lanterna.rendering.lighting.LightingLevel;

/**
 * An enumeration of all the materials that will be rendered. Each material provides some
 * information as to how it should be rendered with different lighting levels.
 */
public enum Material {
  Laser(ANSI.RED, ANSI.RED),
  Iron(
      new TextColor.Indexed(235),
      new TextColor.Indexed(240),
      new TextColor.Indexed(245),
      new TextColor.Indexed(250),
      new TextColor.Indexed(255)
  ),
  Player(ANSI.GREEN, ANSI.GREEN),
  WookieWool(new TextColor.Indexed(235), new TextColor.Indexed(130)),
  Void(ANSI.BLACK, ANSI.BLACK);

  private final TextColor darkest;
  private final TextColor dark;
  private final TextColor medium;
  private final TextColor light;
  private final TextColor lightest;

  /* private */ Material(
      TextColor darkest,
      TextColor dark,
      TextColor medium,
      TextColor light,
      TextColor lightest
  ) {
    this.darkest = darkest;
    this.dark = dark;
    this.medium = medium;
    this.light = light;
    this.lightest = lightest;
  }

  /* private */ Material(
      TextColor invisible,
      TextColor visible
  ) {
    this(invisible, visible, visible, visible, visible);
  }

  /**
   * Lights this {@link Material} with a certain {@link LightingLevel}, and returns the {@link
   * TextColor} that should be used for rendering this {@link Material}.
   *
   * @param level The {@link LightingLevel} used.
   * @return The {@link TextColor} that should be used.
   */
  public TextColor lighted(LightingLevel level) {
    switch (level) {
      case Darkest:
        return darkest;
      case Dark:
        return dark;
      case Medium:
        return medium;
      case Bright:
        return light;
      case Brightest:
        return lightest;
      default:
        return null;
    }
  }

  /**
   * Overlaps a different {@link Material} on top of the current instance, and returns the {@link
   * Material} that should be displayed. In general, the overlapping material will be used, but if
   * the overlapping material is null or {@link Material#Void}, the underlying material will be
   * used.
   *
   * @param other The overlay material.
   * @return A {@link Material} instance.
   */
  public Material overlapped(Material other) {
    if (other != null && other != Void) {
      return other;
    } else {
      return this;
    }
  }
}
