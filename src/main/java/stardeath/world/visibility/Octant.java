package stardeath.world.visibility;

import stardeath.world.Vector;

/* package */ enum Octant {
  ZERO(1, 0, 0, 1),
  ONE(0, 1, 1, 0),
  TWO(0, -1, 1, 0),
  THREE(-1, 0, 0, 1),
  FOUR(-1, 0, 0, -1),
  FIVE(0, -1, -1, 0),
  SIX(0, 1, -1, 0),
  SEVEN(1, 0, 0, -1);

  private final int xInX;
  private final int yInX;

  private final int xInY;
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
