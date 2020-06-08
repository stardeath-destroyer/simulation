package stardeath;

public abstract class Entity {

  private int x;
  private int y;

  protected Entity(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setPosition(int x, int y) {
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
