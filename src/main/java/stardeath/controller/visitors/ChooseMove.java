package stardeath.controller.visitors;

import java.util.List;
import java.util.Random;
import stardeath.animates.actions.Action;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.movements.Jumper;
import stardeath.animates.participants.movements.MovementVisitor;
import stardeath.animates.participants.movements.Walker;
import stardeath.animates.weapons.ProjectileDirection;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;
import stardeath.controller.interactions.GetDirections;
import stardeath.controller.interactions.GetMovements;
import stardeath.world.Vector;
import stardeath.world.World;

public class ChooseMove extends MovementVisitor {

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
  public <J extends Participant & Jumper> void visitJumper(J jumper) {
    jumper.addAction(jumper.new MoveAction(new Vector(
        random(-jumper.getRange(), jumper.getRange()),
        random(-jumper.getRange(), jumper.getRange())
    )));
  }

  @Override
  public <W extends Participant & Walker> void visitWalker(W walker) {
    walker.addAction(walker.new MoveAction(randomMove()));
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    EnemyVisitor enemyVisitor = new EnemyVisitor(soldier.getFaction());
    TileDetectionVisitor detectionVisitor = new TileDetectionVisitor();

    world.visitVisibleAnimatesFrom(soldier, soldier.getVisibilityRange(), enemyVisitor);
    world.visitVisibleTilesFrom(soldier, detectionVisitor);

    Action actionToSet;
    double random = Math.random();

    // Choose between following the player, random and attacking
    if (enemyVisitor.getPlayer().isPresent() && ! enemyVisitor.getEnemies().isEmpty()) {
      if (random < 0.2) {
        actionToSet = getRandomMove(soldier, detectionVisitor);
      } else if (random < 0.45) {
        actionToSet = followPlayer(soldier, enemyVisitor.getPlayer().get());
      } else {
        actionToSet = attackEnemy(soldier, enemyVisitor);
      }
      // follow the player or random
    } else if (enemyVisitor.getPlayer().isPresent()) {
      if (random < 0.65) {
        actionToSet = getRandomMove(soldier, detectionVisitor);
      } else {
        actionToSet = followPlayer(soldier, enemyVisitor.getPlayer().get());
      }
      // attack !
    } else if (! enemyVisitor.getEnemies().isEmpty()) {
      actionToSet = attackEnemy(soldier, enemyVisitor);
      // default -> random
    } else {
      actionToSet = getRandomMove(soldier, detectionVisitor);
    }

    soldier.addAction(actionToSet);
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

  private static Action attackEnemy(Soldier soldier, EnemyVisitor visitor) {
    List<Participant> enemies = visitor.getEnemies();
    Participant enemy = enemies.get(random(0, enemies.size() - 1));

    return soldier.new Fire(
        new LaserBeam(
            soldier.getPosition(),
            ProjectileDirection.getDirectionsFrom(enemy.getPosition().sub(soldier.getPosition()))
        )
    );
  }

  @Override
  public <P extends Player> void visitPlayer(P player) {
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
}
