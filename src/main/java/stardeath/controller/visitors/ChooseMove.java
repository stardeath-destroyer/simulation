package stardeath.controller.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import stardeath.animates.Action;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.world.Vector;
import stardeath.world.World;
import stardeath.world.tiles.Hole;

/**
 * This visitor will assign moves to Animates
 */
public class ChooseMove implements AnimateVisitor {

  private static final Random sRandom = new Random();
  private final World world;
  private final GetDirections directions;
  private final GetMovements interactions;

  public ChooseMove(World world, GetDirections directions, GetMovements interactions) {
    this.world = world;
    this.directions = directions;
    this.interactions = interactions;
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    // Live and let be
    wookie.addAction(wookie.new MoveAction(randomMove()));
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    EnemyVisitor enemyVisitor = new EnemyVisitor(trooper.getFaction());
    // Normal movement except that we can fly over Holes
    TileDetectionVisitor detectionVisitor = new TileDetectionVisitor() {

      @Override
      public void visitTile(Hole hole) {
      }
    };

    world.visitVisibleAnimatesFrom(trooper, trooper.getVisibilityRange(), enemyVisitor);
    world.visitVisibleTilesFrom(trooper, detectionVisitor);

    // Normal empire stuff
    ActionPicker reducer = baseTrooperReducer(trooper, enemyVisitor, detectionVisitor);
    trooper.addAction(reducer.pick());
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    EnemyVisitor enemyVisitor = new EnemyVisitor(trooper.getFaction());
    TileDetectionVisitor detectionVisitor = new TileDetectionVisitor();

    world.visitVisibleAnimatesFrom(trooper, trooper.getVisibilityRange(), enemyVisitor);
    world.visitVisibleTilesFrom(trooper, detectionVisitor);

    // Normal empire stuff
    ActionPicker reducer = baseTrooperReducer(trooper, enemyVisitor, detectionVisitor);

    // Additionally, launch grenades every now and then
    if (!enemyVisitor.getEnemies().isEmpty()) {
      List<Participant> enemies = new ArrayList<>(enemyVisitor.getEnemies());
      Collections.shuffle(enemies);
      Vector enemyPosition = enemies.get(0).getPosition();

      reducer.add(10,
          trooper.new Fire(
              new Grenade(
                  trooper.getPosition(),
                  ProjectileDirection.getDirectionsFrom(enemyPosition.sub(trooper.getPosition()))
              )
          )
      );
    }

    trooper.addAction(reducer.pick());
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    EnemyVisitor enemyVisitor = new EnemyVisitor(trooper.getFaction());
    TileDetectionVisitor detectionVisitor = new TileDetectionVisitor();

    world.visitVisibleAnimatesFrom(trooper, trooper.getVisibilityRange(), enemyVisitor);
    world.visitVisibleTilesFrom(trooper, detectionVisitor);

    ActionPicker reducer = baseTrooperReducer(trooper, enemyVisitor, detectionVisitor);
    trooper.addAction(reducer.pick());
  }

  private ActionPicker baseTrooperReducer(
      Trooper trooper,
      EnemyVisitor enemyVisitor,
      TileDetectionVisitor detectionVisitor
  ) {
    ActionPicker reducer = new ActionPicker();

    // Attack the player
    if (enemyVisitor.getPlayer().isPresent()) {
      reducer.add(20, attackEnemy(trooper, enemyVisitor.getPlayer().get()));
      reducer.add(5, getRandomMove(trooper, detectionVisitor));
    }

    // Attack rebels
    if (! enemyVisitor.getEnemies().isEmpty()) {
      reducer.add(20, attackRandomFromList(trooper, enemyVisitor.getEnemies()));
      reducer.add(5, getRandomMove(trooper, detectionVisitor));
    }

    // If the player is near, we sometimes attack our own
    if (! enemyVisitor.getFriends().isEmpty() && ! enemyVisitor.getEnemies().isEmpty()) {
      reducer.add(1, attackRandomFromList(trooper, enemyVisitor.getFriends()));
    }

    // Add a bit of random  movements
    reducer.add(5, getRandomMove(trooper, detectionVisitor));
    return reducer;
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    EnemyVisitor enemyVisitor = new EnemyVisitor(soldier.getFaction());
    TileDetectionVisitor detectionVisitor = new TileDetectionVisitor();

    world.visitVisibleAnimatesFrom(soldier, soldier.getVisibilityRange(), enemyVisitor);
    world.visitVisibleTilesFrom(soldier, detectionVisitor);

    ActionPicker reducer = new ActionPicker();

    // Tend to follow the player
    if (enemyVisitor.getPlayer().isPresent()) {
      reducer.add(10, followPlayer(soldier, enemyVisitor.getPlayer().get()));
      reducer.add(6, getRandomMove(soldier, detectionVisitor));
    }

    // Attack enemies
    if (! enemyVisitor.getEnemies().isEmpty()) {
      reducer.add(20, attackRandomFromList(soldier, enemyVisitor.getEnemies()));
    }

    // Add a bit of random  movements
    reducer.add(2, getRandomMove(soldier, detectionVisitor));

    soldier.addAction(reducer.pick());
  }

  /**
   * Helper method that gives us a random move given some constraints
   * @param participant The participant to add a move to
   * @param visitor The visitor that knows what tiles to avoid
   * @return A MoveAction that was random given a set of constraints
   */
  private static Action getRandomMove(Participant participant, TileDetectionVisitor visitor) {
    Vector move;
    boolean chooseAgain;
    int i = 0;

    do {
      move = randomMove();
      i++;
      Vector finalMove = move;
      chooseAgain = visitor.tilesToAvoid().stream()
          .anyMatch(tile -> participant.getPosition().add(finalMove).equals(tile.getPosition()));
    } while (chooseAgain && i < 8);

    if (i >= 8) {
      move = Vector.EMPTY;
    }

    return participant.new MoveAction(move);
  }

  private static Action followPlayer(Participant participant, Player player) {
    return participant.new MoveAction(participant.getPosition().directionTo(player.getPosition()));
  }

  private static Action attackRandomFromList(Soldier soldier, List<Participant> enemies) {
    Participant enemy = enemies.get(random(0, enemies.size() - 1));
    return attackEnemy(soldier, enemy);
  }

  private static Action attackEnemy(Soldier soldier, Participant enemy) {
    return soldier.new Fire(
        new LaserBeam(
            soldier.getPosition(),
            ProjectileDirection.getDirectionsFrom(enemy.getPosition().sub(soldier.getPosition()))
        )
    );
  }

  @Override
  public void visitParticipant(Player player) {
    switch (interactions.requestMovement()) {
      case UP:
        player.addAction(player.new MoveAction(Vector.NORTH));
        break;
      case LEFT:
        player.addAction(player.new MoveAction(Vector.WEST));
        break;
      case DOWN:
        player.addAction(player.new MoveAction(Vector.SOUTH));
        break;
      case RIGHT:
        player.addAction(player.new MoveAction(Vector.EAST));
        break;
      case FIRE:
        player.addAction(player.new Fire(
            new LaserBeam(
                player.getPosition(),
                directions.requestDirectionsFromPlayer())));
        break;
      case GRENADE:
        player.addAction(player.new Fire(
            new Grenade(player.getPosition(), directions.requestDirectionsFromPlayer())
        ));
        break;
      case INTERACT:
        player.addAction(player.new Interact());
        break;
    }

    player.addAction(player.new UnveilAction());
  }

  @Override
  public void visitProjectile(LaserBeam projectile) {
    projectile.addAction(projectile.new MoveAndHit());
  }

  @Override
  public void visitProjectile(Grenade grenade) {
    // If the grenade is read to explode, assign it the explode action otherwise move it
    if (grenade.willExplode()) {
      grenade.addAction(grenade.new Explode());
    } else {
      grenade.addAction(grenade.new MoveAndTrigger());
    }
  }

  private static int random(int min, int max) {
    return sRandom.nextInt(max + 1 - min) + min;
  }

  private static Vector randomMove() {
    return new Vector(
        random(-1, 1),
        random(-1, 1)
    );
  }

  /**
   * Helper class that allows us to map a coefficient to an action and later choose a random action
   * based on the coefficient we assigned to it
   */
  private static class ActionPicker {
    private final List<Choice> actionChoices;

    public ActionPicker() {
      actionChoices = new ArrayList<>();
    }

    /**
     * Adds a new action to the pool of actions
     * @param coefficient The coefficient to assign to the action
     * @param action The action to add
     */
    public void add(int coefficient, Action action) {
      actionChoices.add(new Choice(coefficient, action));
    }

    /**
     * Picks a random action in the pool of actions according to its coefficient
     * @return An action
     */
    public Action pick() {
      int sum = actionChoices.stream()
          .map(choice -> choice.coefficient)
          .reduce(0, Integer::sum);

      int random = sRandom.nextInt(sum);

      return actionChoices.stream()
          .reduce((choice, choice2) -> {
            if (choice.coefficient > random) {
              return choice;
            } else {
              choice2.coefficient += choice.coefficient;
              return choice2;
            }
          }).get().action;
    }

    /**
     * Specialized kind of Pair. Represents an action with a coefficient assigned to it
     *
     * Warning : No encapsulation
     */
    private static class Choice {
      public int coefficient;
      public Action action;

      public Choice(int coefficient, Action action) {
        this.coefficient = coefficient;
        this.action = action;
      }
    }
  }
}
