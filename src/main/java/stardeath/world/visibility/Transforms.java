package stardeath.world.visibility;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import stardeath.world.Vector;

/**
 * Provides some static functions in charge of performing some operations on some x and y
 * coordinates, sometimes by changing their direction based on a provided {@link Octant}.
 */
/* package */ final class Transforms {

  private Transforms() {

  }

  /**
   * Provides a {@link Function} that translates all elements by a certain amount.
   *
   * @param f      The transformed {@link BiFunction}.
   * @param vector The shift amount.
   * @param <T>    The type of return value of the function.
   * @return The transformed {@link BiFunction}.
   */
  public static <T> Function<Vector, T> translateOrigin(
      Function<Vector, T> f,
      Vector vector) {
    return (a) -> f.apply(a.add(vector));
  }

  /**
   * Provides a {@link Consumer} that translates all elements by a certain amount.
   *
   * @param f      The transformed {@link Consumer}.
   * @param vector The shift amount.
   * @return The transformed {@link Consumer}.
   */
  public static Consumer<Vector> translateOrigin(
      Consumer<Vector> f,
      Vector vector) {
    return (a) -> f.accept(a.add(vector));
  }

  /**
   * Provides a {@link Function} that transforms through horizontal and vertical symmetries some
   * content in a provided {@link Octant} to be accessible as if it was coming from the {@link
   * Octant#ZERO}.
   *
   * @param octant The origin {@link Octant}.
   * @param f      The transformed {@link Function}.
   * @param <T>    The type of the return value of the function.
   * @return The transformed {@link Function}.
   */
  public static <T> Function<Vector, T> translateOctant(
      Octant octant,
      Function<Vector, T> f) {
    return original -> f.apply(octant.transformToZero(original));
  }

  /**
   * Provides a {@link Consumer} that transforms through horizontal and vertical symmetries some
   * content in a provided {@link Octant} to be accessible as if it was coming from the {@link
   * Octant#ZERO}.
   *
   * @param octant The origin {@link Octant}.
   * @param f      The transformed {@link Consumer}.
   * @return The transformed {@link Consumer}.
   */
  public static Consumer<Vector> translateOctant(
      Octant octant,
      Consumer<Vector> f) {
    return original -> f.accept(octant.transformToZero(original));
  }
}
