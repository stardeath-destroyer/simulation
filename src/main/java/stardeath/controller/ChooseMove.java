package stardeath.controller;

import java.util.Random;
import stardeath.interactions.GetDirections;
import stardeath.interactions.GetMovements;
import stardeath.participants.Participant;
import stardeath.animates.movements.Jumper;
import stardeath.animates.movements.MovementVisitor;
import stardeath.animates.movements.Walker;
import stardeath.participants.player.Player;
import stardeath.participants.weapons.Projectile;
import stardeath.world.Floor;

public class ChooseMove extends MovementVisitor {

  private static final Random sRandom = new Random();
  private final Floor level;
  private final GetDirections directions;
  private final GetMovements interactions;

  public ChooseMove(Floor level, GetDirections directions, GetMovements interactions) {
    this.level = level;
    this.directions = directions;
    this.interactions = interactions;
  }

  private static int random(int min, int max) {
    return sRandom.nextInt(max + 1 - min) + min;
  }

  @Override
  public <J extends Participant & Jumper> void visitJumper(J jumper) {
    jumper.addAction(jumper.new MoveAction(
        random(-jumper.getRange(), jumper.getRange()),
        random(-jumper.getRange(), jumper.getRange())
    ));
  }

  @Override
  public <W extends Participant & Walker> void visitWalker(W walker) {
    walker.addAction(walker.new MoveAction(
        random(-1, 1),
        random(-1, 1)
    ));
  }

  @Override
  public <P extends Player> void visitPlayer(P player) {
    switch (interactions.requestMovement()) {
      case UP:
        player.addAction(player.new MoveAction(0, -1));
        break;
      case LEFT:
        player.addAction(player.new MoveAction(-1, 0));
        break;
      case DOWN:
        player.addAction(player.new MoveAction(0, 1));
        break;
      case RIGHT:
        player.addAction(player.new MoveAction(1, 0));
        break;
      case FIRE:
        player.addAction(player.new Fire(directions.requestDirectionsFromPlayer(), 1));
        break;
    }
  }

  @Override
  public void visitProjectile(Projectile projectile) {
    projectile.addAction(projectile.new MoveAndHit());
  }
}
