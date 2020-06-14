package stardeath.animates.participants.movements;

import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;

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
  public void visitParticipant(FlameTrooper trooper) {
    visitWalker(trooper);
  }

  @Override
  public void visitParticipant(Trooper trooper) {
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
