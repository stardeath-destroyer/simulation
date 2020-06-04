package stardeath.participants.movements;

public interface MoveableVisitor {

  void visitJumper(Jumper jumper);

  void visitWalker(Walker walker);
}
