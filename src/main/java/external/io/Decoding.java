package external.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stardeath.Main;
import stardeath.participants.Participant;
import stardeath.participants.entities.Soldier;
import stardeath.participants.entities.Wookie;
import stardeath.participants.entities.empire.JumpTrooper;
import stardeath.participants.entities.empire.Trooper;
import stardeath.world.Floor;
import stardeath.world.Tile;
import stardeath.world.tiles.Hole;
import stardeath.world.tiles.Regular;
import stardeath.world.tiles.Start;
import stardeath.world.tiles.Wall;
import stardeath.world.World;

public class Decoding {

  private Decoding() {
  }


  public static World loadWorld(URI worldFile) throws java.io.IOException {

    World world = new World();

    Iterable<Path> paths = FileSystems.newFileSystem(worldFile, Collections.emptyMap()).getRootDirectories();

    int levelnb = 1;
    boolean found = true;

    for(Path path : paths){
      if(!found) break;
      found = false;
      if(path.getFileName().toString().equals("level-" + levelnb) && path.toFile().isDirectory()){
        world.addFloor(readFloor(path.toFile()));
        levelnb++;
        found = true;
      }
    }

        /*
    FileSystems.newFileSystem(worldFile, Collections.emptyMap())
        .getRootDirectories()
        .forEach(root -> {
          try {
            Files.walk(root).forEach(path -> world.addFloor(readFloor(path.toFile())));
          } catch (IOException e) {
            e.printStackTrace();
          }
        });

         */


    return world;
  }

  private static Floor readFloor(File folder){
    if(!folder.isDirectory()) throw new IllegalArgumentException("Not a folder");
    List<Participant> enemies = null;
    Floor floor = null;

    try{
      for (final File fileEntry : folder.listFiles()) {
        String extension = fileEntry.getName().split(".")[1];
        if(extension.equals("floor")){
          floor = new Floor(readTiles(new FileInputStream(fileEntry)).toArray(new Tile[0]));
        }else if(extension.equals("enemies")){
          enemies = readEnemies(new FileInputStream(fileEntry));
        }
      }
    }catch (IOException ex){
      ex.printStackTrace();
      return null;
    }



    if(floor == null) throw new RuntimeException("*.floor file not found");
    if(enemies == null) throw new RuntimeException("*.enemies file not found");
    floor.addParticipants(enemies);

    return floor;
  }

  private static Tile from(char character, int x, int y) {
    switch (character) {
      case 'H':
        return new Hole(x, y);
      case '.':
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
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    final List<Tile> tiles = new ArrayList<>();
    int y = 0;

    String line;
    while((line = reader.readLine()) != null){
      y++;
      for(int x = 0; x < line.length(); ++x) {
          tiles.add(from(line.charAt(x), x, y));
      }
    }
    return tiles;
  }


  public static List<Participant> readEnemies(InputStream input) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    final List<Participant> enemies = new ArrayList<>();
    String line;
    while((line = reader.readLine()) != null){
        enemies.add(parseParticipant(line));
    }
    return enemies;
  }

  private static Participant parseParticipant(String line){
    String[] tokens = line.split(" ");
    int x = Integer.parseInt(tokens[0]);
    int y = Integer.parseInt(tokens[1]);

    char type = (tokens[2].charAt(0));
    switch (type){
      case 'W':
        return new Wookie(x, y);
      case 'S':
        return new Soldier(x, y);
      case 'T':
        return new Trooper(x, y);
      case 'J':
        return new JumpTrooper(x, y);
      default:
        return null;
    }
  }
}
