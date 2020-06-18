package stardeath.world.visibility;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import stardeath.Entity;
import stardeath.world.Vector;

/**
 * A simple ray casting algorithm implementation. This is based on this great article on Microsoft's
 * website : https://docs.microsoft.com/en-us/archive/blogs/ericlippert/shadowcasting-in-c-part-one.
 */
public class RayCasting {

  private RayCasting() {
  }

  /**
   * Computes which cells are visible or hidden from a given position.
   *
   * @param entity The {@link Entity} with its start position.
   * @param radius The radius at which we'll stop computations.
   * @param opaque A {@link Function} that indicates whether a certain position is opaque or not.
   * @param write  The {@link Consumer} that is called when a cell is determined to be visible or
   *               hidden.
   */
  public static void compute(
      Entity entity, int radius,
      Function<Vector, Boolean> opaque,
      Consumer<Vector> write
  ) {

    // We work in each octant.
    for (Octant octant : Octant.values()) {
      computeOctant(
          Transforms.translateOctant(octant,
              Transforms.translateOrigin(opaque, entity.getPosition())),
          Transforms.translateOctant(octant,
              Transforms.translateOrigin(write, entity.getPosition())),
          radius);
    }
  }

  /**
   * Computes the illumination of cells in the octant {@link Octant#ZERO}. This considers that the
   * light source is at position (0, 0).
   *
   * @param radius      The radius at which we'll stop computations.
   * @param opaque      A {@link Function} that indicates whether a certain position is opaque or
   *                    not.
   * @param markVisible The {@link Consumer} that is called when a cell is determined to be visible
   *                    or hidden.
   */
  private static void computeOctant(
      Function<Vector, Boolean> opaque,
      Consumer<Vector> markVisible,
      int radius
  ) {
    // We compute portions.
    Deque<Portion> queue = new ArrayDeque<>();
    queue.addLast(new Portion(0, new Vector(1, 0), new Vector(1, 1)));

    // While there are some remaining portions, compute lighting. This is recursively applied.
    while (queue.size() > 0) {
      Portion current = queue.pollFirst();

      // We only work within a certain radius.
      if (current.getX() < radius) {
        computeColumnPortion(
            current.getX(),
            current.getTop(),
            current.getBottom(),
            opaque,
            markVisible,
            radius,
            queue);
      }
    }
  }

  /**
   * For a given x coordinate, and a top and bottom coordinate pair, compute which cells are visible
   * on that column. If possible, some new portions of the next column will be added to the
   * processing queue.
   *
   * @param x           The x coordinate of the column.
   * @param top         The top of the sub-column to compute.
   * @param bottom      The bottom of the sub-column to compute.
   * @param opaque      A {@link Function} that indicates whether a certain position is opaque or
   *                    not.
   * @param markVisible The {@link Consumer} that is called when a cell is determined to be visible
   *                    or hidden.
   * @param radius      The radius at which we'll stop computations.
   * @param queue       The {@link Deque} that will be used to add new portions to compute.
   */
  private static void computeColumnPortion(
      int x,
      Vector top, Vector bottom,
      Function<Vector, Boolean> opaque,
      Consumer<Vector> markVisible,
      int radius,
      Deque<Portion> queue) {

    // Find the top of the current portion.
    int topY;
    if (x == 0) {
      topY = 0;
    } else {
      int quotient = (2 * x + 1) * top.getY() / (2 * top.getX());
      int remainder = (2 * x + 1) * top.getY() % (2 * top.getX());

      if (remainder > top.getX()) {
        topY = quotient + 1;
      } else {
        topY = quotient;
      }
    }

    // Find the bottom of the current portion.
    int bottomY;
    if (x == 0) {
      bottomY = 0;
    } else {
      int quotient = (2 * x - 1) * bottom.getY() / (2 * bottom.getX());
      int remainder = (2 * x - 1) * bottom.getY() % (2 * bottom.getX());

      if (remainder >= bottom.getX()) {
        bottomY = quotient + 1;
      } else {
        bottomY = quotient;
      }
    }

    // Was the last cell opaque ?
    Optional<Boolean> wasLastCellOpaque = Optional.empty();

    // While the field of view is not closed.
    for (int y = topY; y >= bottomY; --y) {
      boolean inRadius = isInRadius(x, y, radius);
      if (inRadius) {
        markVisible.accept(new Vector(x, y));
      }

      boolean currentIsOpaque = !inRadius || opaque.apply(new Vector(x, y));
      if (wasLastCellOpaque.isPresent()) {
        if (currentIsOpaque) {
          if (!wasLastCellOpaque.get()) {
            // We've found a new portion to compute !
            queue.addLast(new Portion(x + 1, new Vector(x * 2 - 1, y * 2 + 1), top));
          }
        } else if (wasLastCellOpaque.get()) {
          // We've found a new top position.
          top = new Vector(x * 2 + 1, y * 2 + 1);
        }
      }
      // Whether the last cell was actually opaque or not.
      wasLastCellOpaque = Optional.of(currentIsOpaque);
    }

    // Add a new portion.
    if (wasLastCellOpaque.isPresent() && !wasLastCellOpaque.get()) {
      queue.addLast(new Portion(x + 1, bottom, top));
    }
  }

  /**
   * Checks whether some x and y coordinates lie within a certain radius, with special handling of
   * rounding.
   *
   * @param x      The x coordinate to check.
   * @param y      The y coordinate to check.
   * @param length The radius.
   * @return True if the values lie within the range.
   */
  private static boolean isInRadius(int x, int y, int length) {
    return (2 * x - 1) * (2 * x - 1) + (2 * y - 1) * (2 * y - 1) <= 4 * length * length;
  }
}
