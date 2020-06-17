package stardeath.animates;

import stardeath.world.World;

/**
 * Defines what an Action is. An action can simply be executed on an instance of world, i.e. it
 * changes the state of the game
 */
public interface Action {

  static Action of(Action... actions) {
    return world -> {
      for (Action action : actions) {
        action.execute(world);
      }
    };
  }

  /**
   * Changes the state of the given world
   * @param world World to change
   */
  void execute(World world);
}
