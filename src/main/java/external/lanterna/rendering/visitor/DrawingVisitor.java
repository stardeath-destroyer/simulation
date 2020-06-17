package external.lanterna.rendering.visitor;

import external.lanterna.rendering.texturing.Material;
import external.lanterna.rendering.texturing.MaterialRenderer;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;
import stardeath.world.visitors.TileVisitor;

/**
 * This visitor will visit Tiles and Animates to assign them a foreground, a background,
 * using {@link Material}, and a {@link Character}
 */
public class DrawingVisitor extends MaterialRenderer implements AnimateVisitor, TileVisitor {

  public DrawingVisitor(int width, int height) {
    super(width, height);
  }

  @Override
  public void visitTile(DownwardElevator elevator) {
    drawTile(elevator, Material.Iron, Material.ElevatorCarpet, 'v');
  }

  @Override
  public void visitTile(Hole hole) {
    drawTile(hole, Material.Void, Material.Void, ' ');
  }

  @Override
  public void visitTile(Regular regular) {
    drawTile(regular, Material.Iron, Material.Void, '.');
  }

  @Override
  public void visitTile(Start start) {
    drawTile(start, Material.Iron, Material.Void, '.');
  }

  @Override
  public void visitTile(Terminal terminal) {
    if (terminal.isOnline()) {
      drawTile(terminal, Material.FancyPlastic, Material.Void, '@');
    } else {
      drawTile(terminal, Material.BurnedPlastic, Material.Void, '@');
    }
  }

  @Override
  public void visitTile(UpwardElevator elevator) {
    drawTile(elevator, Material.Iron, Material.ElevatorCarpet, '^');
  }

  @Override
  public void visitTile(Wall wall) {
    drawTile(wall, Material.Void, Material.Iron, ' ');
  }

  @Override
  public void visitParticipant(Player player) {
    drawAnimate(player, Material.Player, Material.Void, 'P');
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    drawAnimate(trooper, Material.TrooperArmor, Material.Void, 'J');
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    drawAnimate(trooper, Material.TrooperArmor, Material.Void, 'F');
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    drawAnimate(trooper, Material.TrooperArmor, Material.Void, 'T');
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    drawAnimate(soldier, Material.RebelArmor, Material.Void, 'S');
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    drawAnimate(wookie, Material.WookieWool, Material.Void, 'W');
  }

  @Override
  public void visitProjectile(LaserBeam projectile) {
    drawAnimate(projectile, projectile.getPosition(), Material.Laser, Material.Void, '*');
  }

  @Override
  public void visitProjectile(Grenade grenade) {
    drawAnimate(grenade, grenade.getPosition(), Material.Laser, Material.Void, 'ó');
  }
}
