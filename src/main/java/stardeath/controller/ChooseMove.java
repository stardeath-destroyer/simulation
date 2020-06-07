package stardeath.controller;

import java.util.List;
import java.util.Random;
import stardeath.participants.actions.Action;
import stardeath.participants.actions.Move;
import stardeath.participants.movements.Jumper;
import stardeath.participants.movements.MovementVisitor;
import stardeath.participants.movements.Walker;
import stardeath.world.Tile;

public class ChooseMove extends MovementVisitor {

  private static Random sRandom = new Random();
  private final List<Tile> level;

  public ChooseMove(List<Tile> level) {
    this.level = level;
  }

  int random(int max) {
    return sRandom.nextInt(max + 1);
  }

  @Override
  public Action visitJumper(Jumper jumper) {
    return new Move(random(jumper.getRange()), random(jumper.getRange()));
  }

  @Override
  public Action visitWalker(Walker walker) {
    return new Move(random(1), random(1));
  }
}
