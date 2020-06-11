package stardeath.controller.visitors;

import java.util.Random;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.attacks.AttackVisitor;
import stardeath.animates.participants.attacks.Attacker;
import stardeath.animates.participants.attacks.SplashAttacker;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.weapons.Projectile;
import stardeath.world.Floor;

public class ChooseAttack extends AttackVisitor {

  private static final Random sRandom = new Random();
  private final Floor level;

  public ChooseAttack(Floor level) {
    this.level = level;
  }

  @Override
  public <A extends Participant & Attacker> void visitAttacker(A participant) {

  }

  @Override
  public <S extends Participant & SplashAttacker> void visitSplashAttacker(S participant) {

  }

  @Override
  public void visitParticipant(Player player) {

  }

  @Override
  public void visitProjectile(Projectile projectile) {

  }
}