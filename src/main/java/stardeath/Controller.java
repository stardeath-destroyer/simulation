package stardeath;

import java.util.ArrayList;
import java.util.List;
import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.FlameTrooper;
import stardeath.world.Tile;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Wall;

public class Controller<Renderer extends TileVisitor & ParticipantVisitor> {

  private final Renderer renderer;
  private final List<Tile> level = new ArrayList<>();
  private final List<Participant> players = new ArrayList<>();

  public Controller(Renderer renderer) {
    this.renderer = renderer;
    players.add(new Wookie());
    players.add(new FlameTrooper());
    level.add(new Wall());
  }

  public void step() {
  }

  public void draw() {
    for (Tile tile : level) {
      tile.accept(renderer);
    }
    for (Participant participant : players) {
      participant.accept(renderer);
    }
    System.out.println(renderer.toString());
  }
}
