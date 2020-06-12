package stardeath.controller;

import java.util.List;
import java.util.Random;
import stardeath.animates.actions.ExecuteActions;
import stardeath.animates.participants.entities.Player;
import stardeath.controller.visitors.ChooseMove;
import stardeath.controller.visitors.UnveilVisitor;
import stardeath.controller.visitors.UpdateVisibility;
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

  public Controller(InteractionsFactory factory, World world) {
    this.renderer = factory.renderer();
    this.directions = factory.direction();
    this.movements = factory.movement();
    this.world = world;

    // Register this Controller's ability to render things NOW to the Renderer.
    this.renderer.registerRenderRequestListener(this::draw);

    List<Start> startingTiles = world.current().getStartTiles();
    Start startingTile = startingTiles.get(new Random().nextInt(startingTiles.size()));

    world.current().addAnimate(new Player(startingTile.getX(), startingTile.getY()));
    world.current().spawn();

    discover();
    turn();
    draw();
  }

  private void discover() {
    world.current().visitAnimates(new UnveilVisitor());
  }

  private void move() {
    world.current().visitAnimates(new ChooseMove(world.current(), directions, movements));
  }

  private void turn() {
    world.current().visitAnimates(new ExecuteActions(world));
    world.current().spawn();
    world.current().visitAnimates(new UpdateVisibility());
  }

  public void step() {
    discover();
    move();
    turn();
  }

  public void draw() {
    // Draw the contents.
    renderer.render(world);
  }
}
