package external.lanterna.rendering.lighting;

import stardeath.animates.participants.entities.Player;
import stardeath.world.World;
import stardeath.world.visitors.DefaultTileVisitor;
import stardeath.world.visitors.TileVisitor;

public class LightingShader {

  private final World world;

  public LightingShader(World world) {
    this.world = world;
  }

  public LightingLevel[][] withPlayer(Player player) {
    MarkVisibility visibility = new MarkVisibility(world.current(), player);
    TileVisitor visitor = new DefaultTileVisitor(visibility::withVisibleTile);
    world.visitVisibleTilesFrom(player, player.getVisibilityRange(), visitor);
    return visibility.getLevels();
  }
}
