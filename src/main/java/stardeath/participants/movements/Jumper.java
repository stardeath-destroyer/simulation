package stardeath.participants.movements;

public interface Jumper extends Walker {

  @Override
  default void accept(MoveableVisitor visitor) {
    visitor.visitJumper(this);
  }
}
