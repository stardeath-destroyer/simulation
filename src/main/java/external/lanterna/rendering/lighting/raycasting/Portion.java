package external.lanterna.rendering.lighting.raycasting;

/* package */ class Portion {

  public int x;
  public Vector bottom;
  public Vector top;

  public Portion(int x, Vector bottom, Vector top) {
    this.x = x;
    this.bottom = bottom;
    this.top = top;
  }
}
