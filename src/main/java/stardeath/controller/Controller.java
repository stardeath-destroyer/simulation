package stardeath.controller;

import java.util.List;
import java.util.Random;
import stardeath.animates.actions.ExecuteActions;
import stardeath.animates.participants.entities.Player;
import stardeath.controller.visitors.ChooseMove;
import stardeath.controller.visitors.UnveilVisitor;
import stardeath.controller.visitors.UpdateVisibility;
import stardeath.world.Floor;
import stardeath.world.World;
import stardeath.world.tiles.Start;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.controller.interactions.Renderer;

public class Controller {

  private final Renderer renderer;
  private final GetDirections directions;
  private final GetMovements movements;
  private final World world;

  private Floor currentFloor;

  public Controller(InteractionsFactory factory, World world) {
    this.renderer = factory.renderer();
    this.directions = factory.direction();
    this.movements = factory.movement();
    this.world = world;
    this.currentFloor = world.getFirst();
    
    // Register this Controller's ability to render things NOW to the Renderer.
    this.renderer.registerRenderRequestListener(this::draw);

    List<Start> startingTiles = this.currentFloor.getStartTiles();
    Start startingTile = startingTiles.get(new Random().nextInt(startingTiles.size()));

    currentFloor.addAnimate(new Player(startingTile.getX(), startingTile.getY()));
    currentFloor.spawn();

    discover();
    turn();
    draw();
  }

  private void discover() {
    currentFloor.visitAnimates(new UnveilVisitor());
  }

  private void move() {
    currentFloor.visitAnimates(new ChooseMove(currentFloor, directions, movements));
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
