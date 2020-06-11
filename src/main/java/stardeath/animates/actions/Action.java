package stardeath.animates.actions;

import stardeath.world.Floor;

public interface Action {

  static Action of(Action... actions) {
    return level -> {
      for (Action action : actions) {
        action.execute(level);
      }
    };
  }

  void execute(Floor level);
}
