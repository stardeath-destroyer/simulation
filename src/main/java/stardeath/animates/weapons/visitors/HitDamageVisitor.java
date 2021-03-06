package stardeath.animates.weapons.visitors;

import java.util.Random;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.weapons.Projectile;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;
import stardeath.world.Tile;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

/**
 * This class is used to make blasters hit Entities
 */
public class HitDamageVisitor extends ConsumableVisitor {

  private static final Random random = new Random();
  private final int force;
  private final Projectile projectile;

  public HitDamageVisitor(Projectile projectile, int force) {
    super();
    this.projectile = projectile;
    this.force = force;
  }

  private void hit(Participant participant) {
    projectile.remove();
    participant.damage(this.force);
  }

  private void hit(Tile tile) {
    if (tile.isOpaque()) {
      projectile.remove();
      consumed = true;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Player player) {
    hit(player);
    consumed = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(JumpTrooper trooper) {
    hit(trooper);
    consumed = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(FlameTrooper trooper) {
    hit(trooper);
    consumed = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Trooper trooper) {
    hit(trooper);
    consumed = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Soldier soldier) {
    hit(soldier);
    consumed = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitParticipant(Wookie wookie) {
    hit(wookie);
    consumed = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitProjectile(LaserBeam projectile) {
    // Standard projectiles do not collide.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitProjectile(Grenade grenade) {
    // It's not possible to detonate grenades remotely, sorry.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(DownwardElevator elevator) {
    hit(elevator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Hole hole) {
    hit(hole);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Regular regular) {
    hit(regular);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Start start) {
    hit(start);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Terminal terminal) {
    if (random.nextInt(100) < 15) {
      terminal.destroy();
    }
    consumed = true;
    projectile.remove();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(UpwardElevator elevator) {
    hit(elevator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitTile(Wall wall) {
    hit(wall);
  }
}
