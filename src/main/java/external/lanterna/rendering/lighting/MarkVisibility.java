package external.lanterna.rendering.lighting;

import java.util.Arrays;
import stardeath.animates.participants.entities.Player;
import stardeath.world.Floor;
import stardeath.world.Tile;
import stardeath.world.Vector;

public class MarkVisibility {

  private final Floor floor;
  private final Player player;
  private final LightingLevel[][] levels;

  public MarkVisibility(
      Floor floor,
      Player player
  ) {
    this.floor = floor;
    this.player = player;
    this.levels = new LightingLevel[floor.getWidth()][floor.getHeight()];

    // By default, everything is hidden.
    for (LightingLevel[] levels : levels) {
      Arrays.fill(levels, LightingLevel.Darkest);
    }
  }

  private static int distanceTo(Vector from, Vector to) {
    int deltaX = from.getX() - to.getX();
    int deltaY = from.getY() - to.getY();
    return (int) Math.ceil(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  public void withVisibleTile(Tile tile) {
    int x = tile.getPosition().getX();
    int y = tile.getPosition().getY();
    if (x >= 0 && y >= 0 && x < floor.getWidth() && y < floor.getHeight()) {
      int distance = distanceTo(player.getPosition(), tile.getPosition());
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

  public LightingLevel[][] getLevels() {
    return levels;
  }
}
