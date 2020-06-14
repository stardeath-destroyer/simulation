package external.lanterna.rendering.lighting;

import java.util.Arrays;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.visitors.NoOpAnimateVisitor;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.world.World;
import stardeath.world.visitors.TileVisitor;

/**
 * An implementation of an {@link stardeath.animates.visitors.AnimateVisitor} that takes care of
 * adding appropriate lighting based on the state of the differents elements in the {@link World}.
 */
public class LightingShader extends NoOpAnimateVisitor {

  private final World world;
  private final LightingLevel[][] levels;

  public LightingShader(World world) {
    super();
    this.world = world;
    this.levels = makeLayer();
  }

  /**
   * Creates a new {@link LightingLevel} layer. Sometimes, new layers are required, especially if
   * certain tiles get traversed multiple times.
   *
   * @return The {@link LightingLevel} that's been created with the world dimensions.
   */
  private LightingLevel[][] makeLayer() {
    LightingLevel[][] layer = new LightingLevel[world.getWidth()][world.getHeight()];
    for (LightingLevel[] levels : layer) {
      Arrays.fill(levels, LightingLevel.Darkest);
    }
    return layer;
  }

  /**
   * Adds a {@link LightingLevel} matrix on top of the current shader.
   *
   * @param layer The added layer.
   */
  private void addLayer(LightingLevel[][] layer) {
    for (int i = 0; i < layer.length; i++) {
      for (int j = 0; j < layer[i].length; j++) {
        levels[i][j] = levels[i][j].plus(layer[i][j]);
      }
    }
  }

  /**
   * Returns the aggregated {@link LightingLevel} of this {@link LightingShader}.
   */
  public LightingLevel[][] getLevels() {
    return levels;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Player player) {
    LightingLevel[][] layer = makeLayer();
    TileVisitor visitor = new SetLightingLevelVisitor(
        new LinearDistanceIntensity(player.getPosition(), player.getVisibilityRange()),
        layer
    );
    world.visitVisibleTilesFrom(player, visitor);
    addLayer(layer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitProjectile(Grenade grenade) {
    if (grenade.willExplode()) {
      LightingLevel[][] layer = makeLayer();
      TileVisitor visitor = new SetLightingLevelVisitor(
          new LinearDistanceIntensity(grenade.getPosition(), grenade.getRange()),
          layer
      );
      world.visitVisibleTilesFrom(grenade, grenade.getRange(), visitor);
      addLayer(layer);
    }
  }
}
