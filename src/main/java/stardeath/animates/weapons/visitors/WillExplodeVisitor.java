package stardeath.animates.weapons.visitors;

import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.world.tiles.Wall;

public class WillExplodeVisitor extends ConsumableVisitor {

  private final Grenade grenade;

  private void consume() {
    this.consumed = true;
    grenade.trigger();
  }

  public WillExplodeVisitor(Grenade grenade) {
    this.grenade = grenade;
  }

  @Override
  public void visitParticipant(Player player) {
    consume();
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    consume();
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    consume();
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    consume();
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    consume();
  }

  @Override
  public void visitTile(Wall wall) {
    consume();
  }
}
