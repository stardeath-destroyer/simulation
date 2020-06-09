package external.lanterna.rendering.lighting.raycasting;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import stardeath.participants.player.Player;

public class RayCasting {

  public static boolean[][] compute(
      int width, int height,
      Player player,
      BiFunction<Integer, Integer, Boolean> opaque
  ) {
    boolean[][] visible = new boolean[width][height];
    BiConsumer<Integer, Integer> write = (x, y) -> {
      if (x >= 0 &&
          y >= 0 &&
          x < visible.length &&
          y < visible[x].length) {
        visible[x][y] = true;
      }
    };

    for (int i = 0; i < 8; i++) {
      computeFirstOctant(
          translateOctant(translateOrigin(opaque, player.getX(), player.getY()), i),
          translateOctant(translateOrigin(write, player.getX(), player.getY()), i),
          player.getVisibilityRange());
    }

    return visible;
  }

  private static void computeFirstOctant(
      BiFunction<Integer, Integer, Boolean> opaque,
      BiConsumer<Integer, Integer> markVisible,
      int radius
  ) {
    Deque<Portion> queue = new ArrayDeque<>();
    queue.addLast(new Portion(0, new Vector(1, 0), new Vector(1, 1)));
    while (queue.size() > 0) {
      Portion current = queue.pollFirst();
      if (current.x >= radius) {
        continue;
      }
      computeColumnPortion(
          current.x,
          current.top,
          current.bottom,
          opaque,
          markVisible,
          radius,
          queue);
    }
  }

  private static void computeColumnPortion(
      int x,
      Vector top, Vector bottom,
      BiFunction<Integer, Integer, Boolean> opaque,
      BiConsumer<Integer, Integer> markVisible,
      int radius,
      Deque<Portion> queue) {

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

    Optional<Boolean> wasLastCellOpaque = Optional.empty();
    for (int y = topY; y >= bottomY; --y) {
      boolean inRadius = isInRadius(x, y, radius);
      if (inRadius) {
        markVisible.accept(x, y);
      }

      boolean currentIsOpaque = !inRadius || opaque.apply(x, y);
      if (wasLastCellOpaque.isPresent()) {
        if (currentIsOpaque) {
          if (!wasLastCellOpaque.get()) {
            queue.addLast(new Portion(x + 1, new Vector(x * 2 - 1, y * 2 + 1), top));
          }
        } else if (wasLastCellOpaque.get()) {
          top = new Vector(x * 2 + 1, y * 2 + 1);
        }
      }
      wasLastCellOpaque = Optional.of(currentIsOpaque);
    }

    if (wasLastCellOpaque.isPresent() && !wasLastCellOpaque.get()) {
      queue.addLast(new Portion(x + 1, bottom, top));
    }
  }

  private static boolean isInRadius(int x, int y, int length) {
    return (2 * x - 1) * (2 * x - 1) + (2 * y - 1) * (2 * y - 1) <= 4 * length * length;
  }

  private static <T> BiFunction<Integer, Integer, T> translateOrigin(
      BiFunction<Integer, Integer, T> f,
      int x, int y) {
    return (a, b) -> f.apply(a + x, b + y);
  }

  private static BiConsumer<Integer, Integer> translateOrigin(
      BiConsumer<Integer, Integer> f,
      int x, int y) {
    return (a, b) -> f.accept(a + x, b + y);
  }

  private static <T> BiFunction<Integer, Integer, T> translateOctant(
      BiFunction<Integer, Integer, T> f,
      int octant) {
    switch (octant) {
      default:
        return f;
      case 1:
        return (x, y) -> f.apply(y, x);
      case 2:
        return (x, y) -> f.apply(-y, x);
      case 3:
        return (x, y) -> f.apply(-x, y);
      case 4:
        return (x, y) -> f.apply(-x, -y);
      case 5:
        return (x, y) -> f.apply(-y, -x);
      case 6:
        return (x, y) -> f.apply(y, -x);
      case 7:
        return (x, y) -> f.apply(x, -y);
    }
  }

  private static BiConsumer<Integer, Integer> translateOctant(
      BiConsumer<Integer, Integer> f,
      int octant) {
    switch (octant) {
      default:
        return f;
      case 1:
        return (x, y) -> f.accept(y, x);
      case 2:
        return (x, y) -> f.accept(-y, x);
      case 3:
        return (x, y) -> f.accept(-x, y);
      case 4:
        return (x, y) -> f.accept(-x, -y);
      case 5:
        return (x, y) -> f.accept(-y, -x);
      case 6:
        return (x, y) -> f.accept(y, -x);
      case 7:
        return (x, y) -> f.accept(x, -y);
    }
  }
}
