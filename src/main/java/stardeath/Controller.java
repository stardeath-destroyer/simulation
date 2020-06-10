package stardeath;

import java.util.List;
import java.util.Random;
import stardeath.controller.ChooseMove;
import stardeath.controller.UnveilVisitor;
import stardeath.controller.UpdateVisibility;
import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;
import stardeath.participants.actions.ExecuteActions;
import stardeath.participants.entities.Wookie;
import stardeath.participants.player.Player;
import stardeath.world.Floor;
import stardeath.world.tiles.Start;

public class Controller {

  private final Renderer renderer;
  private final MovementInteractions movements;

  private Floor floor;

  public Controller(UIFactory factory, Floor floor) {
    this.renderer = factory.renderer();
    this.movements = factory.movement();
    this.floor = floor;
    
    // Register this Controller's ability to render things NOW to the Renderer.
    this.renderer.registerRenderRequestListener(this::draw);

    List<Start> startingTiles = this.floor.getStartTiles();
    Start startingTile = startingTiles.get(new Random().nextInt(startingTiles.size()));

    for (int i = 0; i < 42; i++)
      floor.addParticipant(new Wookie(20, 20));

    floor.addParticipant(new Player(startingTile.getX(), startingTile.getY()));

    discover();
    turn();
    draw();
  }

  private void discover() {
    floor.visitAnimates(new UnveilVisitor());
  }

  private void move() {
    floor.visitAnimates(new ChooseMove(floor, movements));
  }

  private void turn() {
    floor.visitAnimates(new ExecuteActions(this.floor));
    floor.visitAnimates(new UpdateVisibility());
  }

  public void step() {
    discover();
    move();
    turn();
  }

  public void draw() {

    // Draw the contents.
    renderer.render(floor);
  }
}
