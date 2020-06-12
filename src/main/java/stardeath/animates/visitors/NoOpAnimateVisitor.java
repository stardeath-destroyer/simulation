package stardeath.animates.visitors;

public class NoOpAnimateVisitor extends DefaultAnimateVisitor {

  public NoOpAnimateVisitor() {
    super(ignored -> {
    });
  }
}
