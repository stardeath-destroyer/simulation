package stardeath.participants.attacks;

public interface AttackableVisitor {

  void visit(Attacker attacker);

  void visit(SplashAttacker attacker);
}
