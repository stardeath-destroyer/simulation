package external.lanterna.rendering.lighting;

import java.util.Arrays;
import java.util.function.BiConsumer;
import stardeath.animates.participants.entities.Player;
import stardeath.world.Floor;

public class MarkVisibility implements BiConsumer<Integer, Integer> {

  private final Floor floor;
  private final Player player;
  private final LightingLevel[][] levels;

  public MarkVisibility(
      Floor floor,
      Player player
  ) {
    this.floor = floor;
    this.player = player;
    this.levels = new LightingLevel[floor.getWidth() + 1][floor.getHeight() + 1];

    // By default, everything is hidden.
    for (LightingLevel[] levels : levels) {
      Arrays.fill(levels, LightingLevel.Darkest);
    }
  }

  private static int distanceTo(int fromX, int fromY, int toX, int toY) {
    int deltaX = fromX - toX;
    int deltaY = fromY - toY;
    return (int) Math.ceil(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  public LightingLevel[][] getLevels() {
    return levels;
  }

  @Override
  public void accept(Integer x, Integer y) {
    if (x >= 0 && y >= 0 && x < floor.getWidth() && y < floor.getHeight()) {
      int distance = distanceTo(player.getX(), player.getY(), x, y);
      if (distance > 10) {
        levels[x][y] = LightingLevel.Dark;
      } else if (distance > 7) {
        levels[x][y] = LightingLevel.Medium;
      } else if (distance > 5) {
        levels[x][y] = LightingLevel.Bright;
      } else {
        levels[x][y] = LightingLevel.Brightest;
      }
    }
  }
}
