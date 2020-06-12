package stardeath.world.visitors;

public abstract class NoOpTileVisitor extends DefaultTileVisitor {

  public NoOpTileVisitor() {
    super(ignored -> {
    });
  }
}
