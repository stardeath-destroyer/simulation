package stardeath;

public abstract class Entity {

  protected int x;
  protected int y;

  protected Entity(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
