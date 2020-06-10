package external.lanterna.rendering.texturing;

import stardeath.Entity;
import stardeath.participants.Participant;
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

  private void drawEntity(Entity entity, Material f, Material b, char c) {
    // Only draw the entity if we are within the bounds.
    int x = entity.getX();
    int y = entity.getY();

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

  protected void drawParticipant(Participant participant, Material f, Material b, char c) {
    drawEntity(participant, f, b, c);
  }

  protected void drawTile(Tile tile, Material f, Material b, char c) {
    if (tile.isDiscovered())
      drawEntity(tile, f, b, c);
  }
}
