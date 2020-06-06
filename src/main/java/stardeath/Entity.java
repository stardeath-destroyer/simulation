package stardeath;

public interface Entity {

  void accept(EntityVisitor visitor);
}
