package external.lanterna.rendering.lighting;

import java.util.function.BiFunction;
import stardeath.animates.participants.entities.Player;
import stardeath.world.World;
import stardeath.world.visibility.RayCasting;

public class LightingShader {

  private final boolean[][] opaque;
  private final int width;
  private final int height;

  private final BiFunction<Integer, Integer, Boolean> isOpaque;
  private final World world;

  public LightingShader(World world) {
    this.width = world.current().getWidth();
    this.height = world.current().getHeight();
    this.world = world;

    this.opaque = new boolean[width][height];

    this.isOpaque = (x, y) -> (x >= 0 && y >= 0 && x < width && y < height && opaque[x][y]);

    world.current().getTiles().forEach(t -> opaque[t.getX()][t.getY()] = t.isOpaque());
  }


  public LightingLevel[][] withPlayer(Player player) {
    MarkVisibility visibility = new MarkVisibility(world.current(), player);
    RayCasting.compute(player, player.getVisibilityRange(), isOpaque, visibility);
    return visibility.getLevels();
  }
}
