package stardeath.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import stardeath.participants.Participant;
import stardeath.participants.actions.Move;
import stardeath.participants.actions.Unveil;
import stardeath.participants.movements.Jumper;
import stardeath.participants.movements.MovementVisitor;
import stardeath.participants.movements.Walker;
import stardeath.participants.player.Player;
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

  @Override
  public <P extends Player> void visitPlayer(P player) {
    System.out.print("Enter your move (w, a, s, d) : ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      boolean keepGoing = true;
      while (keepGoing) {
        keepGoing = false;
        switch (reader.readLine()) {
          case "w":
            player.addAction(new Move(0, -1));
            break;
          case "a":
            player.addAction(new Move(-1, 0));
            break;
          case "s":
            player.addAction(new Move(0, 1));
            break;
          case "d":
            player.addAction(new Move(1, 0));
            break;
          default:
            keepGoing = true;
            System.out.print("Wrong command. Please enter a valid move (w, a, s, d) : ");
            System.out.flush();
        }
      }
    } catch (IOException any) {
      // Too bad.
    }
    player.addAction(new Unveil(level));
  }
}
