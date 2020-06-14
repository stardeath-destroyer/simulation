package external.lanterna.rendering;

import external.lanterna.rendering.texturing.Material;
import external.lanterna.rendering.texturing.MaterialRenderer;
import java.util.Optional;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.visitors.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Wall;

public class RenderingVisitor extends MaterialRenderer implements AnimateVisitor, TileVisitor {

  private Player player;

  public RenderingVisitor(int width, int height) {
    super(width, height);
  }

  public Optional<Player> getPlayer() {
    return Optional.ofNullable(player);
  }

  @Override
  public void visitTile(Armory armory) {
    throw new IllegalStateException("Armories are not supported yet.");
  }

  @Override
  public void visitTile(Dump dump) {
    throw new IllegalStateException("Dumps are not supported yet.");
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
  public void visitTile(UpwardElevator elevator) {
    drawTile(elevator, Material.Iron, Material.ElevatorCarpet, '^');
  }

  @Override
  public void visitTile(Wall wall) {
    drawTile(wall, Material.Void, Material.Iron, ' ');
  }

  @Override
  public void visitParticipant(Player player) {
    this.player = player;
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
