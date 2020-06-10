package stardeath.world.visibility;

import stardeath.world.Vector;

/* package */ class Portion {

  private final int x;
  private final Vector bottom;
  private final Vector top;

  public Portion(int x, Vector bottom, Vector top) {
    this.x = x;
    this.bottom = bottom;
    this.top = top;
  }

  public int getX() {
    return x;
  }

  public Vector getBottom() {
    return bottom;
  }

  public Vector getTop() {
    return top;
  }
}
