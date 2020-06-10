package external.lanterna.rendering;

import external.lanterna.rendering.texturing.Material;
import external.lanterna.rendering.texturing.MaterialRenderer;
import java.util.Optional;
import stardeath.animates.AnimateVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
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
  public void visitTile(Elevator elevator) {
    throw new IllegalStateException("Elevators are not supported yet.");
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
  public void visitTile(Wall wall) {
    drawTile(wall, Material.Void, Material.Iron, ' ');
  }

  @Override
  public void visitParticipant(Player player) {
    this.player = player;
    drawParticipant(player, Material.Player, Material.Void, 'P');
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    throw new IllegalStateException("JumpTroopers are not supported yet.");
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    throw new IllegalStateException("Soldiers are not supported yet.");
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    drawParticipant(wookie, Material.WookieWool, Material.Void, 'W');
  }
}
