package stardeath.world.visitors;

public class NoOpEntityVisitor extends DefaultEntityVisitor {

  public NoOpEntityVisitor() {
    super(t -> {});
  }
}
