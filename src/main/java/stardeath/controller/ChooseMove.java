package stardeath.controller;

import java.util.Random;
import stardeath.interactions.MovementInteractions;
import stardeath.participants.Participant;
import stardeath.participants.actions.Move;
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

  int random(int max) {
    return sRandom.nextInt(max + 1);
  }

  @Override
  public <J extends Participant & Jumper> void visitJumper(J jumper) {
    jumper.addAction(new Move(random(jumper.getRange()), random(jumper.getRange())));
  }

  @Override
  public <W extends Participant & Walker> void visitWalker(W walker) {
    walker.addAction(new Move(random(1), random(1)));
  }

  @Override
  public <P extends Player> void visitPlayer(P player) {
    switch (interactions.requestMovement()) {
      case UP:
        player.addAction(new Move(0, -1));
        break;
      case LEFT:
        player.addAction(new Move(-1, 0));
        break;
      case DOWN:
        player.addAction(new Move(0, 1));
        break;
      case RIGHT:
        player.addAction(new Move(1, 0));
        break;
    }
  }
}
