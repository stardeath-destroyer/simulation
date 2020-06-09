package external.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import stardeath.world.Tile;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Wall;

public class Decoding {

  private Decoding() {
  }

  private static Tile from(char character, int x, int y) {
    switch (character) {
      case '.':
        return new Hole(x, y);
      case 'o':
        return new Regular(x, y);
      case 'X':
        return new Start(x, y);
      case 'w':
        return new Wall(x, y);
      default:
        throw new IllegalArgumentException("Unknown tile of type '" + character + "'.");
    }
  }

  public static List<Tile> readTiles(InputStream input) throws IOException {
    final List<Tile> tiles = new ArrayList<>();
    int x = 0, y = 0;
    int read;
    while ((read = input.read()) != -1) {
      if (read == '\n') {
        x = 0;
        y++;
      } else {
        tiles.add(from((char) read, x++, y));
      }
    }
    return tiles;
  }
}
