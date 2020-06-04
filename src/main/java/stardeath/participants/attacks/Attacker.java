package stardeath.participants.attacks;

public interface Attacker {

  default void accept(AttackableVisitor visitor) {
    visitor.visit(this);
  }
}
