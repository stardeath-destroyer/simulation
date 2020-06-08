package stardeath.rendering;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import stardeath.Entity;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public class OutputStreamRenderer implements Renderer {

  private final char[][] grid = new char[10][10];
  private PrintStream stream;

  public OutputStreamRenderer(OutputStream stream) {
    this.stream = new PrintStream(stream);
    for (int i = 0; i < 10; i++) {
      Arrays.fill(grid[i], ' ');
    }
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    grid[trooper.getX()][trooper.getY()] = 'J';
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    setGrid(soldier, 'S');
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    setGrid(wookie, 'W');
  }

  @Override
  public void visitTile(Regular regular) {
    setGrid(regular, '.');
  }

  @Override
  public void visitTile(Elevator elevator) {
    setGrid(elevator, 'e');
  }

  @Override
  public void visitTile(Wall wall) {
    setGrid(wall, '|');
  }

  @Override
  public void clear() {
    for (int i = 0; i < 10; i++) {
      Arrays.fill(grid[i], ' ');
    }
  }

  @Override
  public void render() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        builder.append(grid[i][j]);
      }
      builder.append('\n');
    }
    stream.println("--");
    stream.println(builder.toString());
  }
  /**
   * Small helper method to set the grid given an Entity and a character to represent it
   * @param entity The Entity to represent
   * @param c The character to represent the entity with
   */
  private void setGrid(Entity entity, char c) {
    grid[entity.getX()][entity.getY()] = c;
  }
}
