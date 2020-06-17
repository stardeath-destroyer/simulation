package stardeath.visitors;

/**
 * NoOp visitor for entities
 */
public class NoOpEntityVisitor extends DefaultEntityVisitor {

  public NoOpEntityVisitor() {
    super(t -> {});
  }
}
