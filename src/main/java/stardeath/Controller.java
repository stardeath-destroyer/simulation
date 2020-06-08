package stardeath;

import java.util.ArrayList;
import java.util.List;
import stardeath.controller.ChooseMove;
import stardeath.participants.Participant;
import stardeath.participants.actions.ExecuteActions;
import stardeath.participants.player.Player;
import stardeath.rendering.Renderer;
import stardeath.world.Floor;

public class Controller {

  private final Renderer renderer;
  private Floor floor;
  private final List<Participant> players = new ArrayList<>();

  public Controller(Renderer renderer, Floor floor) {
    this.renderer = renderer;
    this.floor = floor;

    players.add(new Player(0, 0));
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

    // Draw the contents.
    renderer.render(floor, players);
  }
}
