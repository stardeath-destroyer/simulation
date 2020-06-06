package stardeath;

public interface Entity {

  default int getX() {
    return 0;
  }

  default int getY() {
    return 0;
  }

  void accept(EntityVisitor visitor);
}
