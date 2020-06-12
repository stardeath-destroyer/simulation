package external.lanterna.rendering.texturing;

import stardeath.animates.Animate;
import stardeath.world.Tile;
import stardeath.world.Vector;

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

  public Material[][] getBackground() {
    return background;
  }

  public Material[][] getForeground() {
    return foreground;
  }

  public Character[][] getCharacters() {
    return characters;
  }

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
