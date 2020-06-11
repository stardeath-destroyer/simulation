package stardeath.world;

public class Vector {

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

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Vector add(Vector other) {
    return new Vector(this.x + other.x, this.y + other.y);
  }

  public Vector inverse() {
    return new Vector(-this.x, -this.y);
  }
}
