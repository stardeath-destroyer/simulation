package stardeath;

import java.util.ArrayList;
import java.util.List;
import stardeath.controller.ChooseMove;
import stardeath.participants.Participant;
import stardeath.participants.actions.Action;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.FlameTrooper;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.rendering.Renderer;
import stardeath.world.Tile;
import stardeath.world.tiles.Wall;

public class Controller {

  private final Renderer renderer;
  private final List<Tile> level = new ArrayList<>();
  private final List<Participant> players = new ArrayList<>();

  public Controller(Renderer renderer) {
    this.renderer = renderer;
    players.add(new Wookie());
    players.add(new FlameTrooper());
    players.add(new JumpTrooper());
    level.add(new Wall());
  }

  public void step() {
    ChooseMove move = new ChooseMove(level);
    for (Participant participant : players) {
      participant.accept(move);
    }
  }

  public void draw() {
    renderer.clear();

    for (Tile tile : level) {
      tile.accept(renderer);
    }
    for (Participant participant : players) {
      participant.accept(renderer);
    }

    // Draw the contents.
    renderer.render();
  }
}
