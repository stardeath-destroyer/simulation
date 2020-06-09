package stardeath.participants.actions;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;

public class ExecuteActions implements ParticipantVisitor {

  private void visit(Participant participant) {
    for (Action action : participant.getActions()) {
      action.execute();
    }
    participant.clearActions();
  }

  @Override
  public void visitParticipant(Player player) {
    visit(player);
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    visit(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    visit(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    visit(wookie);
  }
}
