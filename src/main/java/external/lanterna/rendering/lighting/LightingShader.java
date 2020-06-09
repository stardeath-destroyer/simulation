package external.lanterna.rendering.lighting;

import external.lanterna.rendering.lighting.raycasting.RayCasting;
import java.util.Arrays;
import java.util.Collection;
import stardeath.participants.player.Player;
import stardeath.world.Tile;

public class LightingShader {

  private final LightingLevel[][] lighting;
  private final boolean[][] opaque;
  private final int width;
  private final int height;

  public LightingShader(int width, int height, Collection<Tile> tiles) {
    this.width = width;
    this.height = height;

    this.lighting = new LightingLevel[width][height];
    this.opaque = new boolean[width][height];

    for (LightingLevel[] levels : lighting) {
      Arrays.fill(levels, LightingLevel.Darkest);
    }

    tiles.forEach(t -> opaque[t.getX()][t.getY()] = t.isOpaque());
  }

  private static int distanceTo(int fromX, int fromY, int toX, int toY) {
    int deltaX = fromX - toX;
    int deltaY = fromY - toY;
    return (int) Math.ceil(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  public void withPlayer(Player player) {

    // TODO : Write directly in the brightness buffer.
    boolean[][] visible = RayCasting.compute(width, height, player, (x, y) -> {
      if (x >= 0 && y >= 0 && x < width && y < height) {
        return opaque[x][y];
      }
      return false;
    });

    for (int i = 0; i < visible.length; i++) {
      for (int j = 0; j < visible[i].length; j++) {
        if (visible[i][j]) {
          lighting[i][j] = LightingLevel.Brightest;
        }
      }
    }
  }

  public LightingLevel[][] getLighting() {
    return lighting;
  }
}
