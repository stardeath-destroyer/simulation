package stardeath.controller.visitors;

import java.util.Random;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.movements.Jumper;
import stardeath.animates.participants.movements.MovementVisitor;
import stardeath.animates.participants.movements.Walker;
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
    EnnemyVisitor ennemyVisitor = new EnnemyVisitor(soldier.getFaction());
    world.visitVisibleAnimatesFrom(soldier, soldier.getVisibilityRange(), ennemyVisitor);

    Vector move = null;

    if (ennemyVisitor.getPlayerPos().isPresent()) {
      Player player = ennemyVisitor.getPlayerPos().get();
      if (sRandom.nextDouble() < 0.5d) {
        move = soldier.getPosition().directionTo(player.getPosition());
      }
    }

    if (move == null)
      move = randomMove();

    soldier.addAction(soldier.new MoveAction(move));
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
}
