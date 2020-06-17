package stardeath.visitors;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.visitors.TileVisitor;

/**
 * An Entity visitor interface.
 */
public interface EntityVisitor extends TileVisitor, AnimateVisitor {
}
