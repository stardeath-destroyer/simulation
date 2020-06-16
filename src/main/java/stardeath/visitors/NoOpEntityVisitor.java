package stardeath.visitors;

public class NoOpEntityVisitor extends DefaultEntityVisitor {

  public NoOpEntityVisitor() {
    super(t -> {});
  }
}
