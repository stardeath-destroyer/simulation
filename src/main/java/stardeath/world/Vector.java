package stardeath.world;

public class Vector {

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
