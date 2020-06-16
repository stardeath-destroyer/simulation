package external.lanterna.rendering.visitor;

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
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;
import stardeath.world.visitors.EntityVisitor;

/**
 * An implementation of an {@link EntityVisitor} that traverses the structure of elements, and
 * builds some statistics with it.
 */
public class FloorStatisticsVisitor implements EntityVisitor {

  private int totalTerminals;
  private int remainingTerminals;
  private int remainingRebels;
  private int remainingEmpireMembers;
  private int thrownProjectiles;

  private void countTerminal(Terminal terminal) {
    if (terminal.isOnline()) {
      remainingTerminals++;
    }
    totalTerminals++;
  }

  private void countParticipant(Participant participant) {
    switch (participant.getFaction()) {
      case Rebels:
        remainingRebels++;
        break;
      case Empire:
        remainingEmpireMembers++;
        break;
    }
  }

  private void countProjectile(Projectile projectile) {
    thrownProjectiles++;
  }

  @Override
  public void visitParticipant(Player player) {
    countParticipant(player);
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    countParticipant(trooper);
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    countParticipant(trooper);
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    countParticipant(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    countParticipant(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    countParticipant(wookie);
  }

  @Override
  public void visitProjectile(LaserBeam projectile) {
    countProjectile(projectile);
  }

  @Override
  public void visitProjectile(Grenade grenade) {
    countProjectile(grenade);
  }

  @Override
  public void visitTile(DownwardElevator elevator) {
    // Ignored.
  }

  @Override
  public void visitTile(Hole hole) {
    // Ignored.
  }

  @Override
  public void visitTile(Regular regular) {
    // Ignored.
  }

  @Override
  public void visitTile(Start start) {
    // Ignored.
  }

  @Override
  public void visitTile(Terminal terminal) {
    countTerminal(terminal);
  }

  @Override
  public void visitTile(UpwardElevator elevator) {
    // Ignored.
  }

  @Override
  public void visitTile(Wall wall) {
    // Ignored.
  }

  public int getRemainingTerminals() {
    return remainingTerminals;
  }

  public int getRemainingRebels() {
    return remainingRebels;
  }

  public int getRemainingEmpireMembers() {
    return remainingEmpireMembers;
  }

  public int getThrownProjectiles() {
    return thrownProjectiles;
  }

  public int getTotalTerminals() {
    return totalTerminals;
  }
}
