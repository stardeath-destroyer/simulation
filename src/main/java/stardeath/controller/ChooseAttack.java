package stardeath.controller;

import java.util.Random;
import stardeath.participants.Participant;
import stardeath.participants.attacks.AttackVisitor;
import stardeath.participants.attacks.Attacker;
import stardeath.participants.attacks.SplashAttacker;
import stardeath.participants.player.Player;
import stardeath.participants.weapons.Projectile;
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