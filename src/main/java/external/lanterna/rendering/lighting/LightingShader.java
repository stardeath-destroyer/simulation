package external.lanterna.rendering.lighting;

import java.util.Arrays;
import stardeath.participants.player.Player;

public class LightingShader {

  private final LightingLevel[][] lighting;

  public LightingShader(int width, int height) {
    this.lighting = new LightingLevel[width][height];
    for (LightingLevel[] levels : lighting) {
      Arrays.fill(levels, LightingLevel.Darkest);
    }
  }

  private static int distanceTo(int fromX, int fromY, int toX, int toY) {
    int deltaX = fromX - toX;
    int deltaY = fromY - toY;
    return (int) Math.ceil(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  public void withPlayer(Player player) {
    for (int i = 0; i < lighting.length; i++) {
      for (int j = 0; j < lighting[i].length; j++) {
        if (distanceTo(player.getX(), player.getY(), i, j) < player.getVisibilityRange()) {
          lighting[i][j] = LightingLevel.Brightest;
        }
      }
    }
  }

  public LightingLevel[][] getLighting() {
    return lighting;
  }
}
