package stardeath.animates.visitors;

/**
 * Empty Animate visitor. Useful for subclasses that want to target only specific tiles
 */
public class NoOpAnimateVisitor extends DefaultAnimateVisitor {

  public NoOpAnimateVisitor() {
    super(ignored -> {
    });
  }
}
