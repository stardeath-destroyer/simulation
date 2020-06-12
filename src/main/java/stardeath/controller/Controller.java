package stardeath.controller;

import stardeath.animates.actions.ExecuteActions;
import stardeath.animates.participants.entities.Player;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.controller.interactions.Renderer;
import stardeath.controller.visitors.ChooseMove;
import stardeath.controller.visitors.ChooseStartTile;
import stardeath.controller.visitors.UnveilVisitor;
import stardeath.controller.visitors.UpdateVisibility;
import stardeath.world.World;

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

    // Choose a random start tile for the player.
    ChooseStartTile chooseStart = new ChooseStartTile();
    world.current().visitTiles(chooseStart);
    chooseStart.pickRandom()
        .map(tile -> new Player(tile.getPosition()))
        .ifPresent(player -> world.all()
            .forEach(floor -> floor.addAnimate(player)));
    world.current().spawn();

    discover();
    turn();
    draw();
  }

  private void discover() {
    world.current().visitAnimates(new UnveilVisitor());
  }

  private void move() {
    world.current().visitAnimates(new ChooseMove(world, directions, movements));
  }

  private void turn() {
    world.current().visitAnimates(new ExecuteActions(world));
    world.current().visitAnimates(new UnveilVisitor());
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
