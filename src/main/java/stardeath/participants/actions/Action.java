package stardeath.participants.actions;

import stardeath.participants.Participant;

public interface Action {

  static Action of(Action... actions) {
    return participant -> {
      for (Action action : actions) {
        action.execute(participant);
      }
    };
  }

  void execute(Participant participant);
}
