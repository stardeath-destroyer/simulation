package stardeath.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import stardeath.animates.Animate;
import stardeath.animates.participants.Participant;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.visitors.TileVisitor;

/**
 * A floor of the stardeath
 */
public final class Floor {

  private final Tile[][] tiles;
  private final List<Animate> animates;
  private final List<Animate> spawned;

  private int width;
  private int height;

  private Floor(List<Tile> tiles, List<? extends Animate> animates) {
    tiles.forEach(tile -> {
      width = Math.max(tile.getPosition().getX() + 1, width);
      height = Math.max(tile.getPosition().getY() + 1, height);
    });

    this.tiles = new Tile[width][height];
    this.animates = new ArrayList<>(animates);
    this.spawned = new ArrayList<>();

    for (Tile tile : tiles) {
      this.tiles[tile.getPosition().getX()][tile.getPosition().getY()] = tile;
    }
  }

  /**
   * Returns the width of the floor
   * @return the width of the floor
   */
  public final int getWidth() {
    return width;
  }

  /**
   * Returns the height of the floor
   * @return the height of the floor
   */
  public final int getHeight() {
    return height;
  }

  /**
   * Adds an animate on the floor
   * @param animate The animate to add
   */
  public void addAnimate(Animate animate) {
    spawned.add(animate);
  }

  /**
   * Updates the animates that are on the floor
   */
  public void spawn() {
    animates.addAll(spawned);
    spawned.clear();
    animates.removeIf(Animate::shouldRemove);
  }

  /**
   * Returns the animate at the given position if it exists
   * @param position The position to search
   * @return An optional value. The animate at the position or empty
   */
  public Optional<Animate> participantAt(Vector position) {
    return animates.stream()
        .filter(p -> p.getPosition().equals(position))
        .findFirst();
  }

  /**
   * Returns the tile at the given position if the position is in the floors boundaries
   * @param position The position to search
   * @return An optional value. The tile at the position or empty
   */
  public Optional<Tile> tileAt(Vector position) {
    if (position.getX() >= 0 && position.getY() >= 0 &&
        position.getX() < tiles.length && position.getY() < tiles[position.getX()].length) {
      return Optional.ofNullable(tiles[position.getX()][position.getY()]);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Visits all the animates on the floor
   * @param visitor The visitor to visit the animates with
   */
  public void visitAnimates(AnimateVisitor visitor) {
    animates.forEach(a -> a.accept(visitor));
  }

  /**
   * Visits all the tiles on the floor
   * @param visitor The visitor to visit the tiles with
   */
  public void visitTiles(TileVisitor visitor) {
    for (Tile[] row : tiles) {
      for (Tile tile : row) {
        if (tile != null) {
          tile.accept(visitor);
        }
      }
    }
  }

  public static class Builder {

    private final List<Tile> tiles = new ArrayList<>();
    private final List<Participant> participants = new ArrayList<>();

    public Builder addTile(Tile tile) {
      tiles.add(tile);
      return this;
    }

    public Builder addParticipant(Participant participant) {
      participants.add(participant);
      return this;
    }

    public Floor build() {
      return new Floor(tiles, participants);
    }
  }
}
