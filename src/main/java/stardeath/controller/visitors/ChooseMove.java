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

  private static int random(int min, int max) {
    return sRandom.nextInt(max + 1 - min) + min;
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    wookie.addAction(wookie.new MoveAction(randomMove()));
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    EnemyVisitor enemyVisitor = new EnemyVisitor(trooper.getFaction());
    TileDetectionVisitor detectionVisitor = new TileDetectionVisitor() {

      @Override
      public void visitTile(Hole hole) {
      }
    };

    world.visitVisibleAnimatesFrom(trooper, trooper.getVisibilityRange(), enemyVisitor);
    world.visitVisibleTilesFrom(trooper, detectionVisitor);

    ActionPicker reducer = baseTrooperReducer(trooper, enemyVisitor, detectionVisitor);
    trooper.addAction(reducer.pick());
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    EnemyVisitor enemyVisitor = new EnemyVisitor(trooper.getFaction());
    TileDetectionVisitor detectionVisitor = new TileDetectionVisitor();

    world.visitVisibleAnimatesFrom(trooper, trooper.getVisibilityRange(), enemyVisitor);
    world.visitVisibleTilesFrom(trooper, detectionVisitor);

    ActionPicker reducer = baseTrooperReducer(trooper, enemyVisitor, detectionVisitor);

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

    if (enemyVisitor.getPlayer().isPresent()) {
      reducer.add(20, attackEnemy(trooper, enemyVisitor.getPlayer().get()));
      reducer.add(5, getRandomMove(trooper, detectionVisitor));
    }

    if (! enemyVisitor.getEnemies().isEmpty()) {
      reducer.add(20, attackRandomFromList(trooper, enemyVisitor.getEnemies()));
      reducer.add(5, getRandomMove(trooper, detectionVisitor));
    }

    if (! enemyVisitor.getFriends().isEmpty() && ! enemyVisitor.getEnemies().isEmpty()) {
      reducer.add(1, attackRandomFromList(trooper, enemyVisitor.getFriends()));
    }

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

    if (enemyVisitor.getPlayer().isPresent()) {
      reducer.add(10, followPlayer(soldier, enemyVisitor.getPlayer().get()));
      reducer.add(6, getRandomMove(soldier, detectionVisitor));
    }

    if (! enemyVisitor.getEnemies().isEmpty()) {
      reducer.add(20, attackRandomFromList(soldier, enemyVisitor.getEnemies()));
    }

    reducer.add(2, getRandomMove(soldier, detectionVisitor));

    soldier.addAction(reducer.pick());
  }

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
    if (grenade.willExplode()) {
      grenade.addAction(grenade.new Explode());
    } else {
      grenade.addAction(grenade.new MoveAndTrigger());
    }
  }

  private static Vector randomMove() {
    return new Vector(
        random(-1, 1),
        random(-1, 1)
    );
  }

  private static class ActionPicker {
    List<Choice> actionChoices;

    public ActionPicker() {
      actionChoices = new ArrayList<>();
    }

    public void add(int coefficient, Action action) {
      actionChoices.add(new Choice(coefficient, action));
    }

    public Action pick() {
      int sum = actionChoices.stream()
          .map(choice -> choice.coefficient)
          .reduce(0, Integer::sum);

      int random = sRandom.nextInt(sum);

      Action value = actionChoices.stream()
          .reduce((choice, choice2) -> {
            if (choice.coefficient > random) {
              return choice;
            } else {
              choice2.coefficient += choice.coefficient;
              return choice2;
            }
          }).get().action;
      return value;
    }

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
