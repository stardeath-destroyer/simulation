package stardeath.controller;

import stardeath.controller.visitors.ExecuteActions;
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
    world.visitTiles(chooseStart);
    chooseStart.pickRandom()
        .map(tile -> new Player(tile.getPosition()))
        .ifPresent(player -> world.all()
            .forEach(floor -> floor.addAnimate(player)));
    world.spawn();

    world.visitAnimates(new UnveilVisitor());
    turn();
    draw();
  }

  private void move() {
    world.visitAnimates(new ChooseMove(world, directions, movements));
  }

  private void turn() {
    world.visitAnimates(new ExecuteActions(world));
    world.spawn();
    world.visitAnimates(new UpdateVisibility());
  }

  public void step() {
    move();
    turn();
  }

  public void draw() {
    // Draw the contents.
    renderer.render(world);
  }
}
