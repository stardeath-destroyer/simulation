package stardeath.participants.movements;

public interface Walker {

  default void accept(MoveableVisitor visitor) {
    visitor.visitWalker(this);
  }
}
