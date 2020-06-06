package stardeath.rendering;

import java.util.Arrays;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public class Renderer implements ParticipantVisitor, TileVisitor {

  private char[][] grid = new char[10][10];

  public Renderer() {
    for (int i = 0; i < 10; i++) {
      Arrays.fill(grid[i], ' ');
    }
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    grid[soldier.getX()][soldier.getY()] = 'S';
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    grid[wookie.getX()][wookie.getY()] = 'W';
  }

  @Override
  public void visitTile(Regular regular) {
    grid[regular.getX()][regular.getY()] = 'r';
  }

  @Override
  public void visitTile(Elevator elevator) {
    grid[elevator.getX()][elevator.getY()] = 'e';
  }

  @Override
  public void visitTile(Wall wall) {
    grid[wall.getX()][wall.getY()] = 'w';
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        builder.append(grid[i][j]);
      }
      builder.append('\n');
    }
    return builder.toString();
  }
}
