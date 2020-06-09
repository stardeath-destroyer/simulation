package stardeath.controller;

import java.util.Random;
import stardeath.interactions.MovementInteractions;
import stardeath.participants.Participant;
import stardeath.participants.movements.Jumper;
import stardeath.participants.movements.MovementVisitor;
import stardeath.participants.movements.Walker;
import stardeath.participants.player.Player;
import stardeath.world.Floor;

public class ChooseMove extends MovementVisitor {

  private static final Random sRandom = new Random();
  private final Floor level;
  private final MovementInteractions interactions;

  public ChooseMove(Floor level, MovementInteractions interactions) {
    this.level = level;
    this.interactions = interactions;
  }

  private static int random(int min, int max) {
    return sRandom.nextInt(max + 1 - min) + min;
  }

  @Override
  public <J extends Participant & Jumper> void visitJumper(J jumper) {
    jumper.addAction(jumper.new MoveAction(
        random(-jumper.getRange(), jumper.getRange()),
        random(-jumper.getRange(), jumper.getRange()),
        level
    ));
  }

  @Override
  public <W extends Participant & Walker> void visitWalker(W walker) {
    walker.addAction(walker.new MoveAction(
        random(-1, 1),
        random(-1, 1),
        level
    ));
  }

  @Override
  public <P extends Player> void visitPlayer(P player) {
    switch (interactions.requestMovement()) {
      case UP:
        player.addAction(player.new MoveAction(0, -1, level));
        break;
      case LEFT:
        player.addAction(player.new MoveAction(-1, 0, level));
        break;
      case DOWN:
        player.addAction(player.new MoveAction(0, 1, level));
        break;
      case RIGHT:
        player.addAction(player.new MoveAction(1, 0, level));
        break;
    }
  }
}
