package external.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import stardeath.animates.participants.Participant;
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
import stardeath.world.tiles.UpwardElevator;
import stardeath.world.tiles.Wall;

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
        String name = entry.getName();
        String type = entry.isDirectory() ? "DIR" : "FILE";
        System.out.println(type + " " + name);

        if (entry.isDirectory()) {
          System.out.println("name: " + entry.getName());
          if (!entry.getName().equals("world/")) {
            levelNb = Integer.parseInt(entry.getName()
                .substring(entry.getName().length() - 2, entry.getName().length() - 1));
            System.out.println(levelNb);
          }
        } else {
          if (entry.getName().endsWith(".floor")) {
            floorFile = worldFile.getInputStream(entry);
          } else if (entry.getName().endsWith(".enemies")) {
            enemiesFile = worldFile.getInputStream(entry);
          }
          if (enemiesFile != null && floorFile != null) {
            builder.addFloor(levelNb, readFloor(floorFile, enemiesFile));
            floorFile.close();
            floorFile = null;
            enemiesFile.close();
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

  private static Tile tileFromChar(char character, Vector position) {
    switch (character) {
      case 'H':
        return new Hole(position);
      case '.':
        return new Regular(position);
      case 'X':
        return new Start(position);
      case 'w':
        return new Wall(position);
      case '^':
        return new UpwardElevator(position);
      case 'v':
        return new DownwardElevator(position);
      default:
        throw new IllegalArgumentException("Unknown tile of type '" + character + "'.");
    }
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
      default:
        throw new IllegalStateException("Given map does not follow format.");
    }
  }
}
