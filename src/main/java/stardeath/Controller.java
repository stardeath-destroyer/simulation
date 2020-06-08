package stardeath;

import java.util.ArrayList;
import java.util.List;
import stardeath.controller.ChooseMove;
import stardeath.participants.Participant;
import stardeath.participants.actions.ExecuteActions;
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
