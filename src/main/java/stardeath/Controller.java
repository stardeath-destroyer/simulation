package stardeath;

import java.util.ArrayList;
import java.util.List;
import stardeath.controller.ChooseMove;
import stardeath.participants.Participant;
import stardeath.participants.actions.ExecuteActions;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.FlameTrooper;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.rendering.Renderer;
import stardeath.world.Floor;
import stardeath.world.Tile;

public class Controller {

  private final Renderer renderer;
  private Floor floor;
  private final List<Participant> players = new ArrayList<>();

  public Controller(Renderer renderer, Floor floor) {
    this.renderer = renderer;
    this.floor = floor;
    players.add(new Wookie(1, 2));
    players.add(new FlameTrooper(3, 4));
    players.add(new JumpTrooper(1, 4));
  }

  public void step() {
    ChooseMove move = new ChooseMove(floor);
    for (Participant participant : players) {
      participant.accept(move);
    }
    ExecuteActions execute = new ExecuteActions();
    for (Participant participant : players) {
      participant.accept(execute);
    }
  }

  public void draw() {
    renderer.clear();

    for (Tile tile : floor.getTiles()) {
      tile.accept(renderer);
    }
    for (Participant participant : players) {
      participant.accept(renderer);
    }

    // Draw the contents.
    renderer.render();
  }
}
