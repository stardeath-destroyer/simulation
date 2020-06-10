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
    RayCasting.compute(player, (x, y) -> {
          if (x >= 0 && y >= 0 && x < width && y < height) {
            return opaque[x][y];
          }
          return false;
        }, (x, y) -> {
          if (x >= 0 && y >= 0 && x < width && y < height) {
            int distance = distanceTo(player.getX(), player.getY(), x, y);
            if (distance > 10) {
              lighting[x][y] = LightingLevel.Dark;
            } else if (distance > 7) {
              lighting[x][y] = LightingLevel.Medium;
            } else if (distance > 5) {
              lighting[x][y] = LightingLevel.Bright;
            } else {
              lighting[x][y] = LightingLevel.Brightest;
            }

          }
        }
    );
  }

  public LightingLevel[][] getLighting() {
    return lighting;
  }
}
