package stardeath;

import java.util.List;
import java.util.Random;
import stardeath.controller.ChooseMove;
import stardeath.controller.UnveilVisitor;
import stardeath.controller.UpdateVisibility;
import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;
import stardeath.participants.actions.ExecuteActions;
import stardeath.participants.player.Player;
import stardeath.world.Floor;
import stardeath.world.World;
import stardeath.world.tiles.Start;

public class Controller {

  private final Renderer renderer;
  private final MovementInteractions movements;
  private final World world;

  private Floor currentFloor;

  public Controller(UIFactory factory, World world) {
    this.renderer = factory.renderer();
    this.movements = factory.movement();
    this.world = world;
    this.currentFloor = world.getFirst();
    
    // Register this Controller's ability to render things NOW to the Renderer.
    this.renderer.registerRenderRequestListener(this::draw);

    List<Start> startingTiles = this.currentFloor.getStartTiles();
    Start startingTile = startingTiles.get(new Random().nextInt(startingTiles.size()));

    currentFloor.addAnimate(new Player(startingTile.getX(), startingTile.getY()));

    discover();
    turn();
    draw();
  }

  private void discover() {
    currentFloor.visitAnimates(new UnveilVisitor());
  }

  private void move() {
    currentFloor.visitAnimates(new ChooseMove(currentFloor, movements));
  }

  private void turn() {
    currentFloor.visitAnimates(new ExecuteActions(this.currentFloor));
    currentFloor.spawn();
    currentFloor.visitAnimates(new UpdateVisibility());
  }

  public void step() {
    discover();
    move();
    turn();
  }

  public void draw() {

    // Draw the contents.
    renderer.render(currentFloor);
  }
}
