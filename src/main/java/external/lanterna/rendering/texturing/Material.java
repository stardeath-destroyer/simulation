package external.lanterna.rendering.texturing;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import external.lanterna.rendering.lighting.LightingLevel;

/**
 * An enumeration of all the materials that will be rendered. Each material provides some
 * information as to how it should be rendered with different lighting levels.
 */
public enum Material {
  ElevatorCarpet(
      new TextColor.Indexed(52),
      new TextColor.Indexed(53),
      new TextColor.Indexed(54),
      new TextColor.Indexed(55),
      new TextColor.Indexed(56)
  ),
  Laser(new TextColor.Indexed(196), new TextColor.Indexed(196)),
  Iron(
      new TextColor.Indexed(235),
      new TextColor.Indexed(240),
      new TextColor.Indexed(245),
      new TextColor.Indexed(250),
      new TextColor.Indexed(255)
  ),
  FancyPlastic(
      new TextColor.RGB(128, 0, 94),
      new TextColor.RGB(153, 0, 112),
      new TextColor.RGB(179, 0, 131),
      new TextColor.RGB(204, 0, 150),
      new TextColor.RGB(230, 0, 168)
  ),
  BurnedPlastic(
      new TextColor.RGB(26, 9, 0),
      new TextColor.RGB(51, 18, 0),
      new TextColor.RGB(77, 27, 0),
      new TextColor.RGB(102, 36, 0),
      new TextColor.RGB(128, 43, 0)
  ),

  TrooperArmor(new TextColor.Indexed(124), new TextColor.Indexed(88)),
  RebelArmor(new TextColor.Indexed(11), new TextColor.Indexed(184)),

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
