package stardeath.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import stardeath.animates.Animate;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.Participant;
import stardeath.world.tiles.Start;
import stardeath.world.visitors.NoOpTileVisitor;
import stardeath.world.visitors.TileVisitor;

public final class Floor {

  private final List<Tile> tiles;
  private final List<Animate> animates;
  private final List<Animate> spawned;

  private Floor previous;
  private Floor next;
  private int width;
  private int height;

  public Floor(Tile... tiles) {
    this.tiles = Stream.of(tiles).collect(Collectors.toList());
    this.animates = new ArrayList<>();
    this.spawned = new ArrayList<>();
    this.tiles.forEach(tile -> {
      width = Math.max(tile.getX(), width);
      height = Math.max(tile.getY(), height);
    });
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

  public void addParticipants(List<Participant> newParticipants){
    spawned.addAll(newParticipants);
  }

  public void removeParticipant(Participant participant) {
    animates.remove(participant);
  }

  public void spawn() {
    animates.addAll(spawned);
    spawned.clear();
    animates.removeIf(Animate::shouldRemove);
  }

  public Optional<Animate> getParticipant(int x, int y) {
    return animates.stream()
        .filter(p -> p.getX() == x && p.getY() == y)
        .findFirst();
  }

  public Optional<Tile> getTile(int x, int y) {
    return tiles.stream()
        .filter(t -> t.getX() == x && t.getY() == y)
        .findFirst();
  }

  public Tile tileAt(int x, int y) {
    return tiles.stream().filter(t -> t.getX() == x && t.getY() == y).findFirst().get();
  }

  public final List<Tile> getTiles() {
    return Collections.unmodifiableList(tiles);
  }

  public List<Animate> getParticipants() {
    return Collections.unmodifiableList(animates);
  }

  public final List<Start> getStartTiles() {
    List<Start> tiles = new ArrayList<>();
    visitTiles(new NoOpTileVisitor() {
      @Override
      public void visitTile(Start start) {
        tiles.add(start);
      }
    });
    return tiles;
  }

  public void visitAnimates(AnimateVisitor visitor) {
    animates.forEach(a -> a.accept(visitor));
  }

  public void visitTiles(TileVisitor visitor) {
    for (Tile tile : tiles) {
      tile.accept(visitor);
    }
  }
}
