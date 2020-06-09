package stardeath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import stardeath.controller.ChooseMove;
import stardeath.controller.UnveilVisitor;
import stardeath.interactions.MovementInteractions;
import stardeath.interactions.Renderer;
import stardeath.interactions.Renderer.OnRenderRequestListener;
import stardeath.participants.Participant;
import stardeath.participants.actions.ExecuteActions;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;
import stardeath.world.Floor;
import stardeath.world.tiles.Start;

public class Controller {

  private final Renderer renderer;
  private final MovementInteractions movements;

  private Floor floor;
  private final List<Participant> players = new ArrayList<>();

  public Controller(UIFactory factory, Floor floor) {
    this.renderer = factory.renderer();
    this.movements = factory.movement();
    this.floor = floor;
    
    // Register this Controller's ability to render things NOW to the Renderer.
    this.renderer.registerRenderRequestListener(this::draw);

    List<Start> startingTiles = this.floor.getStartTiles();
    Start startingTile = startingTiles.get(new Random().nextInt(startingTiles.size()));

    for (int i = 0; i < 42; i++)
      players.add(new Wookie(20, 20));
    players.add(new Player(startingTile.getX(), startingTile.getY()));

    discover();
    turn();
    draw();
  }

  private void discover() {
    UnveilVisitor visitor = new UnveilVisitor(floor);
    players.forEach(p -> p.accept(visitor));
  }

  private void move() {
    ChooseMove move = new ChooseMove(floor, movements);
    players.forEach(p -> p.accept(move));
  }

  private void turn() {
    ExecuteActions execute = new ExecuteActions();
    players.forEach(p -> p.accept(execute));
  }

  public void step() {
    discover();
    move();
    turn();
  }

  public void draw() {

    // Draw the contents.
    renderer.render(floor, players);
  }
}
