package stardeath.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.world.tiles.Start;

public final class Floor {

  private final List<Tile> tiles;
  private final List<Participant> participants;

  private Floor previous;
  private Floor next;
  private int width;
  private int height;

  public Floor(Tile... tiles) {
    this.tiles = Stream.of(tiles).collect(Collectors.toList());
    this.participants = new ArrayList<>();
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

  public void addParticipant(Participant participant) {
    participants.add(participant);
  }

  public void removeParticipant(Participant participant) {
    participants.remove(participant);
  }

  public Optional<Participant> getParticipant(int x, int y) {
    return participants.stream()
        .filter(p -> p.getX() == x && p.getY() == y)
        .findFirst();
  }

  public final List<Tile> getTiles() {
    return Collections.unmodifiableList(tiles);
  }

  public List<Participant> getParticipants() {
    return Collections.unmodifiableList(participants);
  }

  public final List<Start> getStartTiles() {
    List<Start> tiles = new ArrayList<>();
    visitTiles(new TileVisitorAdapter() {
      @Override
      public void visitTile(Start start) {
        tiles.add(start);
      }
    });
    return tiles;
  }

  public void visitParticipants(ParticipantVisitor visitor) {
    participants.forEach(participant -> participant.accept(visitor));
  }

  public void visitTiles(TileVisitor visitor) {
    for (Tile tile : tiles) {
      tile.accept(visitor);
    }
  }
}
