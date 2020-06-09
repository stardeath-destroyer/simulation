package stardeath.participants.actions;

public interface Action {

  static Action of(Action... actions) {
    return () -> {
      for (Action action : actions) {
        action.execute();
      }
    };
  }

  void execute();
}
