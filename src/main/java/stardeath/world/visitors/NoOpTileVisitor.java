package stardeath.world.visitors;

/**
 * This visitor will have no action on any tile. It is made to be overriden by subclasses
 */
public abstract class NoOpTileVisitor extends DefaultTileVisitor {

  public NoOpTileVisitor() {
    super(ignored -> {
    });
  }
}
