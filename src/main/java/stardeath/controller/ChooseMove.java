package stardeath.controller;

import java.util.Random;
import stardeath.participants.Participant;
import stardeath.participants.actions.Move;
import stardeath.participants.movements.Jumper;
import stardeath.participants.movements.MovementVisitor;
import stardeath.participants.movements.Walker;
import stardeath.world.Floor;

public class ChooseMove extends MovementVisitor {

  private static Random sRandom = new Random();
  private final Floor level;

  public ChooseMove(Floor level) {
    this.level = level;
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
}
