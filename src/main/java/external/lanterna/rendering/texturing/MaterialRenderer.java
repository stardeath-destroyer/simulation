package external.lanterna.rendering.texturing;

import stardeath.animates.Animate;
import stardeath.world.Tile;
import stardeath.world.Vector;

/**
 * This abstract class represents a way to render different materials. It is a way to abstract
 * different ways of representing an Entity.
 */
public abstract class MaterialRenderer {

  private final Material[][] foreground;
  private final Material[][] background;
  private final Character[][] characters;
  private final boolean[][] drawn;

  public MaterialRenderer(int width, int height) {
    this.foreground = new Material[width][height];
    this.background = new Material[width][height];
    this.characters = new Character[width][height];
    this.drawn = new boolean[width][height];
  }

  private static boolean inBounds(int value, int includedMin, int excludedMax) {
    return value >= includedMin && value < excludedMax;
  }

  /**
   * Gets the background to draw
   * @return A bi-demensionnal array of {@link Material}
   */
  public Material[][] getBackground() {
    return background;
  }

  /**
   * Gets the foreground to draw
   * @return A bi-demensionnal array of {@link Material}
   */
  public Material[][] getForeground() {
    return foreground;
  }

  /**
   * Gets the characters to draw
   * @return A bi-demensionnal array of {@link Character}
   */
  public Character[][] getCharacters() {
    return characters;
  }

  /**
   * Returns a bi-demensionnal array of booleans.
   * Each boolean will be set at true if the element needs to be drawn.
   * @return A bi-demensionnal array of booleans
   */
  public boolean[][] getDrawn() {
    return drawn;
  }

  private void drawAt(Vector position, Material f, Material b, char c) {

    // Only draw the entity if we are within the bounds.
    if (inBounds(position.getX(), 0, characters.length) &&
        inBounds(position.getY(), 0, characters[position.getX()].length)) {

      foreground[position.getX()][position.getY()] = Material.Void
          .overlapped(foreground[position.getX()][position.getY()])
          .overlapped(f);

      background[position.getX()][position.getY()] = Material.Void
          .overlapped(background[position.getX()][position.getY()])
          .overlapped(b);

      characters[position.getX()][position.getY()] = c;
      drawn[position.getX()][position.getY()] = true;
    }
  }

  protected void drawAnimate(Animate animate, Material f, Material b, char c) {
    if (animate.isVisible()) {
      drawAnimate(animate, animate.getPosition(), f, b, c);
    }
  }

  protected void drawAnimate(Animate animate, Vector position, Material f, Material b, char c) {
    if (animate.isVisible()) {
      drawAt(position, f, b, c);
    }
  }

  protected void drawTile(Tile tile, Material f, Material b, char c) {
    if (tile.isDiscovered()) {
      drawAt(tile.getPosition(), f, b, c);
    }
  }
}
