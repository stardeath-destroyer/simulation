package stardeath.animates.actions;

import stardeath.world.World;

public interface Action {

  static Action of(Action... actions) {
    return world -> {
      for (Action action : actions) {
        action.execute(world);
      }
    };
  }

  void execute(World world);
}
