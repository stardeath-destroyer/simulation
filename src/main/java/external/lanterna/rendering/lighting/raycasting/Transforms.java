package external.lanterna.rendering.lighting.raycasting;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* package */ final class Transforms {

  private Transforms() {

  }

  public static <T> BiFunction<Integer, Integer, T> translateOrigin(
      BiFunction<Integer, Integer, T> f,
      int x, int y) {
    return (a, b) -> f.apply(a + x, b + y);
  }

  public static BiConsumer<Integer, Integer> translateOrigin(
      BiConsumer<Integer, Integer> f,
      int x, int y) {
    return (a, b) -> f.accept(a + x, b + y);
  }

  public static <T> BiFunction<Integer, Integer, T> translateOctant(
      BiFunction<Integer, Integer, T> f,
      Octant octant) {
    return (x, y) -> {
      Vector vector = octant.transformToZero(new Vector(x, y));
      return f.apply(vector.getX(), vector.getY());
    };
  }

  public static BiConsumer<Integer, Integer> translateOctant(
      BiConsumer<Integer, Integer> f,
      Octant octant) {
    return (x, y) -> {
      Vector vector = octant.transformToZero(new Vector(x, y));
      f.accept(vector.getX(), vector.getY());
    };
  }
}
