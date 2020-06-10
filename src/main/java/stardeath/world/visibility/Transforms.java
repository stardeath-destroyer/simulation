package stardeath.world.visibility;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import stardeath.world.Vector;

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
      Octant octant,
      BiFunction<Integer, Integer, T> f) {
    return (x, y) -> {
      Vector vector = octant.transformToZero(new Vector(x, y));
      return f.apply(vector.getX(), vector.getY());
    };
  }

  public static BiConsumer<Integer, Integer> translateOctant(
      Octant octant,
      BiConsumer<Integer, Integer> f) {
    return (x, y) -> {
      Vector vector = octant.transformToZero(new Vector(x, y));
      f.accept(vector.getX(), vector.getY());
    };
  }
}
