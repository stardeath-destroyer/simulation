package external.lanterna.rendering.texturing;

import stardeath.Entity;

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

  protected void drawEntity(Entity entity, Material f, Material b, char c) {
    // Only draw the entity if we are within the bounds.
    if (inBounds(entity.getX(), 0, characters.length) &&
        inBounds(entity.getY(), 0, characters[entity.getX()].length)) {
      foreground[entity.getX()][entity.getY()] =
          Material.Void
              .overlapped(foreground[entity.getX()][entity.getY()])
              .overlapped(f);
      background[entity.getX()][entity.getY()] =
          Material.Void
              .overlapped(background[entity.getX()][entity.getY()])
              .overlapped(b);
      characters[entity.getX()][entity.getY()] = c;
      drawn[entity.getX()][entity.getY()] = true;
    }
  }
}
