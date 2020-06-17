package stardeath.world;

import java.util.Objects;

/**
 * A class representing a position or direction
 */
public final class Vector {

  public static final Vector EMPTY = new Vector(0, 0);

  public static final Vector NORTH = new Vector(0, -1);
  public static final Vector EAST = new Vector(1, 0);
  public static final Vector WEST = new Vector(-1, 0);
  public static final Vector SOUTH = new Vector(0, 1);

  public static final Vector NORTH_EAST = NORTH.add(EAST);
  public static final Vector NORTH_WEST = NORTH.add(WEST);
  public static final Vector SOUTH_EAST = SOUTH.add(EAST);
  public static final Vector SOUTH_WEST = SOUTH.add(WEST);

  private final int x;
  private final int y;

  public Vector(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x coordinate
   * @return The x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y coordinate
   * @return The y coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * Adds a vector to another. Returns a newly created vector
   * @param other The other vector to add
   * @return A newly created vector which is the addition of two other
   */
  public Vector add(Vector other) {
    return new Vector(this.x + other.x, this.y + other.y);
  }

  /**
   * Subtracts a vector to another. Returns a newly created vector.
   * The subtraction is equivalent to vector1.add(vector2.inverse())
   * @param other The vector to subtract
   * @return A newly created vector which is the subtraction of two other
   */
  public Vector sub(Vector other) {
    return add(other.inverse());
  }

  /**
   * Returns a newly created vector based on the current one, but with a new x coordinate
   * @param x The x coordinate to set
   * @return A newly created vector with its x coordinate being set by the argument
   */
  public Vector withX(int x) {
    return new Vector(x, this.y);
  }

  /**
   * Returns a newly created vector based on the current one, but with a new y coordinate
   * @param y The y coordinate to set
   * @return A newly created vector with its y coordinate being set by the argument
   */
  public Vector withY(int y) {
    return new Vector(this.x, y);
  }

  /**
   * Returns the inverse of a vector. Equivalent to multiplying the vector by -1
   * Technically, it's the opposite of a vector...
   * @return A newly created vector which is the opposite of the calling one
   */
  public Vector inverse() {
    return new Vector(-this.x, -this.y);
  }

  /**
   * Returns a new vector pointing in the direction of other. This vector will be a unit vector
   * @param other The vector we would like to point towards
   * @return A new unit vector pointing to other
   */
  public Vector directionTo(Vector other) {
    int deltaX = other.getX() - getX();
    int deltaY = other.getY() - getY();

    return new Vector(
        clamp(deltaX, -1, 1),
        clamp(deltaY, -1, 1)
    );
  }

  /**
   * Get the distance to another vector
   * @param other The other vector to get the distance to
   * @return The distance in steps to another vector
   */
  public int distanceTo(Vector other) {
    int deltaX = getX() - other.getX();
    int deltaY = getY() - other.getY();
    return (int) Math.ceil(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vector vector = (Vector) o;
    return x == vector.x &&
        y == vector.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  /**
   * Clamps the x coordinate of a vector between the min and the max
   * @param min Lower bound
   * @param max Upper bound
   * @return A new vector bounded in x by min and max
   */
  public Vector clampX(int min, int max) {
    return new Vector(
        clamp(this.x, min, max),
        this.y
    );
  }

  /**
   * Clamps the y coordinate of a vector between the min and the max
   * @param min Lower bound
   * @param max Upper bound
   * @return A new vector bounded in y by min and max
   */
  public Vector clampY(int min, int max) {
    return new Vector(
        this.x,
        clamp(this.y, min, max)
    );
  }

  private static int clamp(int val, int min, int max) {
    return Math.min(Math.max(val, min), max);
  }
}
