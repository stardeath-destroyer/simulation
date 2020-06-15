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
    world.visitVisibleAnimatesFrom(soldier, soldier.getVisibilityRange(), enemyVisitor);

    Action actionToSet;

    if (enemyVisitor.getPlayerPos().isPresent() && ! enemyVisitor.getEnemies().isEmpty()) {
      actionToSet = chooseFrom(
          followPlayer(soldier, enemyVisitor),
          attackEnemy(soldier, enemyVisitor),
          0.45
      );
    } else if (enemyVisitor.getPlayerPos().isPresent()) {
      actionToSet = followPlayer(soldier, enemyVisitor);
    } else if (! enemyVisitor.getEnemies().isEmpty()) {
      actionToSet = attackEnemy(soldier, enemyVisitor);
    } else {
      actionToSet = soldier.new MoveAction(randomMove());
    }

    soldier.addAction(actionToSet);
  }

  private Action followPlayer(Participant participant, EnemyVisitor visitor) {
    Player player = visitor.getPlayerPos().get();
    return chooseFrom(
        participant.new MoveAction(participant.getPosition().directionTo(player.getPosition())),
        participant.new MoveAction(randomMove()),
        0.5
    );
  }

  private Action attackEnemy(Soldier soldier, EnemyVisitor visitor) {
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

  private Vector randomMove() {
    return new Vector(
        random(-1, 1),
        random(-1, 1)
    );
  }

  private static Action chooseFrom(Action action1, Action action2, double percentage) {
    if (sRandom.nextDouble() < percentage)
      return action1;
    else
      return action2;
  }
}
