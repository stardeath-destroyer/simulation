package stardeath.participants.movements;

import stardeath.participants.ParticipantVisitor;
import stardeath.participants.actions.Action;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;

public abstract class MovementVisitor implements ParticipantVisitor {

  public abstract Action visitJumper(Jumper participant);
  public abstract Action visitWalker(Walker participant);

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    Action.of(visitJumper(trooper), visitWalker(trooper)).execute(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    visitWalker(soldier).execute(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    visitWalker(wookie).execute(wookie);
  }
}
