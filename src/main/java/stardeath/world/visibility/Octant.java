package stardeath.world.visibility;

import stardeath.world.Vector;

/**
 * An enumeration that represents the eight octants, and the transforms that should be applied to
 * map vectors from them to the {@link Octant#ZERO} space.
 */
/* package */ enum Octant {
  ZERO(1, 0, 0, 1),
  ONE(0, 1, 1, 0),
  TWO(0, -1, 1, 0),
  THREE(-1, 0, 0, 1),
  FOUR(-1, 0, 0, -1),
  FIVE(0, -1, -1, 0),
  SIX(0, 1, -1, 0),
  SEVEN(1, 0, 0, -1);

  /*
   * How many times we apply the old x in the new x.
   */
  private final int xInX;

  /*
   * How many times we apply the old y in the new x.
   */
  private final int yInX;

  /*
   * How many times we apply the old x in the new y.
   */
  private final int xInY;

  /*
   * How many times we apply the old y in the new y.
   */
  private final int yInY;

  /* private */ Octant(int xInx, int yInX, int xInY, int yInY) {
    this.xInX = xInx;
    this.yInX = yInX;
    this.xInY = xInY;
    this.yInY = yInY;
  }

  public Vector transformToZero(Vector vector) {
    return new Vector(
        vector.getX() * xInX + vector.getY() * yInX,
        vector.getX() * xInY + vector.getY() * yInY
    );
  }
}
