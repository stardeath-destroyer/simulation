package stardeath.rendering;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.player.Player;
import stardeath.world.Floor;
import stardeath.world.Tile;
import stardeath.world.TileVisitor;
import stardeath.world.tiles.Armory;
import stardeath.world.tiles.Dump;
import stardeath.world.tiles.Elevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Wall;

public class OutputStreamRenderer implements Renderer {

  private final OutputStream stream;

  public OutputStreamRenderer(OutputStream stream) {
    this.stream = stream;
  }

  @Override
  public void render(Floor floor, Collection<Participant> participants) {
    int maxX = 0, maxY = 0;
    for (Tile tile : floor.getTiles()) {
      maxX = Math.max(maxX, tile.getX() + 1);
      maxY = Math.max(maxY, tile.getY() + 1);
    }

    char[][] elements = new char[maxY][maxX];

    renderFloor(elements, floor);
    renderParticipants(elements, participants);

    try {
      for (char[] line : elements) {
        for (char tile : line) {
          stream.write(tile);
        }
        stream.write('\n');
      }
    } catch (IOException any) {
      // Too bad.
    }
  }

  private void renderFloor(char[][] buffer, Floor floor) {
    floor.visit(new TileVisitor() {

      private void setGrid(Tile tile, char symbol) {
        buffer[tile.getY()][tile.getX()] = symbol;
      }

      @Override
      public void visitTile(Armory armory) {
        setGrid(armory, 'A');
      }

      @Override
      public void visitTile(Dump dump) {
        setGrid(dump, 'D');
      }

      @Override
      public void visitTile(Regular regular) {
        setGrid(regular, '.');
      }

      @Override
      public void visitTile(Elevator elevator) {
        setGrid(elevator, '_');
      }

      @Override
      public void visitTile(Hole hole) {
        setGrid(hole, ' ');
      }

      @Override
      public void visitTile(Wall wall) {
        setGrid(wall, 'x');
      }
    });
  }
  private void renderParticipants(char[][] buffer, Collection<Participant> participants) {
    ParticipantVisitor visitor = new ParticipantVisitor() {

      private void setGrid(Participant participant, char symbol) {
        buffer[participant.getY()][participant.getX()] = symbol;
      }

      @Override
      public void visitParticipant(Player player) {
        setGrid(player, 'P');
      }

      @Override
      public void visitParticipant(JumpTrooper trooper) {
        setGrid(trooper, 'T');
      }

      @Override
      public void visitParticipant(Soldier soldier) {
        setGrid(soldier, 'S');
      }

      @Override
      public void visitParticipant(Wookie wookie) {
        setGrid(wookie, 'W');
      }
    };
    participants.forEach(p -> p.accept(visitor));
  }
}
