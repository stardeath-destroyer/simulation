package stardeath.controller;

import java.util.List;
import java.util.Random;
import stardeath.participants.actions.Action;
import stardeath.participants.actions.Move;
import stardeath.participants.movements.Jumper;
import stardeath.participants.movements.MoveableVisitor;
import stardeath.participants.movements.Walker;
import stardeath.world.Tile;

public class ChooseMove implements MoveableVisitor {

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
    return new Move(random(4), random(4));
  }

  @Override
  public Action visitWalker(Walker walker) {
    return new Move(random(1), random(1));
  }
}
