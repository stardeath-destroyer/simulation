package stardeath.participants.attacks;

public interface SplashAttacker extends Attacker {

  @Override
  default void accept(AttackableVisitor visitor) {
    visitor.visit(this);
  }
}
