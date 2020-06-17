package stardeath.visitors;

import stardeath.animates.visitors.AnimateVisitor;
import stardeath.world.visitors.TileVisitor;

public interface EntityVisitor extends TileVisitor, AnimateVisitor {
}
