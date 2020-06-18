package stardeath.animates.visitors;

import java.util.function.Consumer;
import stardeath.animates.Animate;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.weapons.entities.Grenade;
import stardeath.animates.weapons.entities.LaserBeam;

/**
 * Applies a default Consumer to all kind of Animates
 */
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
}
