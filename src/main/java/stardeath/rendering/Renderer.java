package stardeath.rendering;

import stardeath.EntityVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public class Renderer implements EntityVisitor {

  private String buffer = "";

  @Override
  public void visitEntity(Soldier soldier) {
    buffer += "S";
  }

  @Override
  public void visitEntity(Wookie wookie) {
    buffer += "W";
  }

  @Override
  public void visitEntity(Regular regular) {
    buffer += "r";
  }

  @Override
  public void visitEntity(Elevator elevator) {
    buffer += "e";
  }

  @Override
  public void visitEntity(Wall wall) {
    buffer += "w";
  }

  @Override
  public String toString() {
    return buffer;
  }
}
