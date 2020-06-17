package stardeath.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import stardeath.animates.Animate;
import stardeath.animates.participants.Participant;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.visitors.TileVisitor;

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

  public final int getWidth() {
    return width;
  }

  public final int getHeight() {
    return height;
  }

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

  public Optional<Animate> participantAt(Vector position) {
    return animates.stream()
        .filter(p -> p.getPosition().equals(position))
        .findFirst();
  }

  public Optional<Tile> tileAt(Vector position) {
    if (position.getX() >= 0 && position.getY() >= 0 &&
        position.getX() < tiles.length && position.getY() < tiles[position.getX()].length) {
      return Optional.ofNullable(tiles[position.getX()][position.getY()]);
    } else {
      return Optional.empty();
    }
  }

  public void visitAnimates(AnimateVisitor visitor) {
    animates.forEach(a -> a.accept(visitor));
  }

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
