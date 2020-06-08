package stardeath.rendering;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import stardeath.participants.Participant;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.world.Tile;
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
   * Small helper method to set the grid given a participant and a character to represent it
   * @param p The participant to represent
   * @param c The character to represent the participant with
   */
  private void setGrid(Participant p, char c) {
    grid[p.getX()][p.getY()] = c;
  }

  /**
   * Small helper method to set the grid given a Tile and a character to represent it
   * @param t The Tile to represent
   * @param c The character to represent the tile with
   */
  private void setGrid(Tile t, char c) {
    grid[t.getX()][t.getY()] = c;
  }
}
