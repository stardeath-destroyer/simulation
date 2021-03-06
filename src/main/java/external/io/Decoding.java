package external.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.world.Floor;
import stardeath.world.Floor.Builder;
import stardeath.world.Tile;
import stardeath.world.Vector;
import stardeath.world.World;
import stardeath.world.tiles.DownwardElevator;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Terminal;
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

/**
 * A class that will read our map format and setup the game
 */
public class Decoding {

  private Decoding() {}

  public static World loadWorld(ZipFile worldFile) {

    World.Builder builder = new World.Builder();

    try {
      Enumeration<? extends ZipEntry> entries = worldFile.entries();

      int levelNb = 0;
      InputStream floorFile = null;
      InputStream enemiesFile = null;

      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        final String name = entry.getName();

        if (entry.isDirectory() && ! name.equals("world/")) {
          String levelString = name
              .replaceAll("world/level-", "")
              .replaceAll("/", "");
          levelNb = Integer.parseInt(levelString);
        } else {
          if (name.endsWith(".floor")) {
            floorFile = worldFile.getInputStream(entry);
          } else if (name.endsWith(".enemies")) {
            enemiesFile = worldFile.getInputStream(entry);
          }

          if (enemiesFile != null && floorFile != null) {
            builder.addFloor(levelNb, readFloor(floorFile, enemiesFile));
            floorFile.close();
            enemiesFile.close();

            floorFile = null;
            enemiesFile = null;
          }
        }
      }

      worldFile.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return builder.build();
  }

  private static Floor readFloor(InputStream floorFile, InputStream enemiesFile)
      throws IOException {
    Floor.Builder builder = new Builder();

    readTiles(floorFile, builder);
    readEnemies(enemiesFile, builder);

    return builder.build();
  }

  public static void readTiles(InputStream input, Floor.Builder builder) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    String line;
    Vector position = new Vector(0, 0);

    while ((line = reader.readLine()) != null) {
      while (position.getX() < line.length()) {
        builder.addTile(tileFromChar(line.charAt(position.getX()), position));
        position = position.add(Vector.EAST);
      }
      position = position.add(Vector.SOUTH).withX(0);
    }
  }

  private static Tile tileFromChar(char character, Vector position) {
    Tile tile;
    switch (character) {
      case 'H':
        tile = new Hole(position);
        break;
      case '.':
        tile =  new Regular(position);
        break;
      case 'X':
        tile =  new Start(position);
        break;
      case 'w':
        tile =  new Wall(position);
        break;
      case '^':
        tile =  new UpwardElevator(position);
        break;
      case 'v':
        tile =  new DownwardElevator(position);
        break;
      case '@':
        tile =  new Terminal(position);
        break;
      default:
        throw new IllegalArgumentException("Unknown tile of type '" + character + "'.");
    }
    return tile;
  }

  public static void readEnemies(InputStream input, Floor.Builder builder) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    String line;
    while ((line = reader.readLine()) != null) {
      builder.addParticipant(parseParticipant(line));
    }
  }

  private static Participant parseParticipant(String line) {
    String[] tokens = line.split(" ");
    Vector position = new Vector(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

    char type = (tokens[2].charAt(0));
    switch (type) {
      case 'W':
        return new Wookie(position);
      case 'S':
        return new Soldier(position);
      case 'T':
        return new Trooper(position);
      case 'J':
        return new JumpTrooper(position);
      case 'F':
        return new FlameTrooper(position);
      default:
        throw new IllegalStateException("Given map does not follow format.");
    }
  }
}
