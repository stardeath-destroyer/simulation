package external.lanterna.rendering.texturing;

import stardeath.animates.Animate;
import stardeath.world.Tile;

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

  private void drawAt(int x, int y, Material f, Material b, char c) {

    // Only draw the entity if we are within the bounds.
    if (inBounds(x, 0, characters.length) && inBounds(y, 0, characters[x].length)) {

      foreground[x][y] = Material.Void
          .overlapped(foreground[x][y])
          .overlapped(f);

      background[x][y] = Material.Void
          .overlapped(background[x][y])
          .overlapped(b);

      characters[x][y] = c;
      drawn[x][y] = true;
    }
  }

  protected void drawAnimate(Animate animate, Material f, Material b, char c) {
    if (animate.isVisible()) {
      drawAnimate(animate, animate.getX(), animate.getY(), f, b, c);
    }
  }

  protected void drawAnimate(Animate animate, int x, int y, Material f, Material b, char c) {
    if (animate.isVisible()) {
      drawAt(x, y, f, b, c);
    }
  }

  protected void drawTile(Tile tile, Material f, Material b, char c) {
    if (tile.isDiscovered()) {
      drawAt(tile.getX(), tile.getY(), f, b, c);
    }
  }
}
