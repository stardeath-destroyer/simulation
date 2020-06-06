package stardeath;

import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public interface EntityVisitor {

  void visitEntity(Soldier soldier);

  void visitEntity(Wookie wookie);

  void visitEntity(Regular regular);

  void visitEntity(Elevator elevator);

  void visitEntity(Wall wall);
}
