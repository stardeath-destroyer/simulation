package stardeath.animates.visitors;

import java.util.function.Consumer;
import stardeath.animates.Animate;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.weapons.entities.LaserBeam;

public class DefaultAnimateVisitor implements AnimateVisitor {

  private final Consumer<Animate> consumer;

  public DefaultAnimateVisitor(Consumer<Animate> consumer) {
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
}
