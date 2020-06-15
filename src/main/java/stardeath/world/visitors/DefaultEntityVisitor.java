package stardeath.world.visitors;

import java.util.function.Consumer;
import stardeath.Entity;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

public class DefaultEntityVisitor implements EntityVisitor {
  private Consumer<Entity> consumer;

  public DefaultEntityVisitor(Consumer<Entity> consumer) {
    this.consumer = consumer;
  }


  @Override
  public void visitParticipant(Player player) {
    consumer.accept(player);
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    consumer.accept(trooper);
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    consumer.accept(trooper);
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    consumer.accept(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    consumer.accept(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    consumer.accept(wookie);
  }

  @Override
  public void visitProjectile(LaserBeam projectile) {
    consumer.accept(projectile);
  }

  @Override
  public void visitProjectile(Grenade grenade) {
    consumer.accept(grenade);
  }

  @Override
  public void visitTile(Armory armory) {
    consumer.accept(armory);
  }

  @Override
  public void visitTile(Dump dump) {
    consumer.accept(dump);
  }

  @Override
  public void visitTile(DownwardElevator elevator) {
    consumer.accept(elevator);
  }

  @Override
  public void visitTile(Hole hole) {
    consumer.accept(hole);
  }

  @Override
  public void visitTile(Regular regular) {
    consumer.accept(regular);
  }

  @Override
  public void visitTile(Start start) {
    consumer.accept(start);
  }

  @Override
  public void visitTile(Terminal terminal) {
    consumer.accept(terminal);
  }

  @Override
  public void visitTile(UpwardElevator elevator) {
    consumer.accept(elevator);
  }

  @Override
  public void visitTile(Wall wall) {
    consumer.accept(wall);
  }
}
