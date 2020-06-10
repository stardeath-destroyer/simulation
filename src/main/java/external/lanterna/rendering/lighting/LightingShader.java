package external.lanterna.rendering.lighting;

import stardeath.world.visibility.RayCasting;
import java.util.function.BiFunction;
import stardeath.participants.player.Player;
import stardeath.world.Floor;

public class LightingShader {

  private final boolean[][] opaque;
  private final int width;
  private final int height;

  private final BiFunction<Integer, Integer, Boolean> isOpaque;
  private final Floor floor;

  public LightingShader(Floor floor) {
    this.width = floor.getWidth() + 1;
    this.height = floor.getHeight() + 1;
    this.floor = floor;

    this.opaque = new boolean[width][height];

    this.isOpaque = (x, y) -> (x >= 0 && y >= 0 && x < width && y < height && opaque[x][y]);

    floor.getTiles().forEach(t -> opaque[t.getX()][t.getY()] = t.isOpaque());
  }


  public LightingLevel[][] withPlayer(Player player) {
    MarkVisibility visibility = new MarkVisibility(floor, player);
    RayCasting.compute(player, isOpaque, visibility);
    return visibility.getLevels();
  }
}
