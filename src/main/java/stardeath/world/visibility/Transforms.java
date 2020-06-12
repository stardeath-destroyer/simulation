package stardeath.world.visibility;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import stardeath.world.Vector;

/**
 * Provides some static functions in charge of performing some operations on some x and y
 * coordinates, sometimes by changing their direction based on a provided {@link Octant}.
 */
/* package */ final class Transforms {

  private Transforms() {

  }

  /**
   * Provides a {@link BiFunction} that translates all elements by a certain amount.
   *
   * @param f   The transformed {@link BiFunction}.
   * @param x   The x shift amount.
   * @param y   The y shift amount.
   * @param <T> The type of return value of the function.
   * @return The transformed {@link BiFunction}.
   */
  public static <T> BiFunction<Integer, Integer, T> translateOrigin(
      BiFunction<Integer, Integer, T> f,
      int x, int y) {
    return (a, b) -> f.apply(a + x, b + y);
  }

  /**
   * Provides a {@link BiConsumer} that translates all elements by a certain amount.
   *
   * @param f The transformed {@link BiConsumer}.
   * @param x The x shift amount.
   * @param y The y shift amount.
   * @return The transformed {@link BiConsumer}.
   */
  public static BiConsumer<Integer, Integer> translateOrigin(
      BiConsumer<Integer, Integer> f,
      int x, int y) {
    return (a, b) -> f.accept(a + x, b + y);
  }

  /**
   * Provides a {@link BiFunction} that transforms through horizontal and vertical symmetries some
   * content in a provided {@link Octant} to be accessible as if it was coming from the {@link
   * Octant#ZERO}.
   *
   * @param octant The origin {@link Octant}.
   * @param f      The transformed {@link BiFunction}.
   * @param <T>    The type of the return value of the function.
   * @return The transformed {@link BiFunction}.
   */
  public static <T> BiFunction<Integer, Integer, T> translateOctant(
      Octant octant,
      BiFunction<Integer, Integer, T> f) {
    return (x, y) -> {
      Vector vector = octant.transformToZero(new Vector(x, y));
      return f.apply(vector.getX(), vector.getY());
    };
  }

  /**
   * Provides a {@link BiConsumer} that transforms through horizontal and vertical symmetries some
   * content in a provided {@link Octant} to be accessible as if it was coming from the {@link
   * Octant#ZERO}.
   *
   * @param octant The origin {@link Octant}.
   * @param f      The transformed {@link BiConsumer}.
   * @return The transformed {@link BiConsumer}.
   */
  public static BiConsumer<Integer, Integer> translateOctant(
      Octant octant,
      BiConsumer<Integer, Integer> f) {
    return (x, y) -> {
      Vector vector = octant.transformToZero(new Vector(x, y));
      f.accept(vector.getX(), vector.getY());
    };
  }
}
