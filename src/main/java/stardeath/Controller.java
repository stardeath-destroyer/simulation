package stardeath;

import java.util.ArrayList;
import java.util.List;
import stardeath.controller.ChooseMove;
import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;
import stardeath.participants.Participant;
import stardeath.participants.actions.ExecuteActions;
import stardeath.participants.player.Player;
import stardeath.world.Floor;

public class Controller {

  private final Renderer renderer;
  private final MovementInteractions movements;

  private Floor floor;
  private final List<Participant> players = new ArrayList<>();

  public Controller(UIFactory factory, Floor floor) {
    this.renderer = factory.renderer();
    this.movements = factory.movement();
    this.floor = floor;

    players.add(new Player(0, 0));
  }

  public void step() {
    ChooseMove move = new ChooseMove(floor, movements);
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
