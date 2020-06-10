package stardeath.animates.movements;

import stardeath.animates.AnimateVisitor;
import stardeath.participants.Participant;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public abstract class MovementVisitor implements AnimateVisitor {

  public abstract <P extends Player> void visitPlayer(P player);
  public abstract <J extends Participant & Jumper> void visitJumper(J participant);
  public abstract <W extends Participant & Walker> void visitWalker(W participant);

  @Override
  public void visitParticipant(Player player) {
    visitPlayer(player);
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    visitJumper(trooper);
    visitWalker(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    visitWalker(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    visitWalker(wookie);
  }
}
