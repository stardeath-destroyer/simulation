package stardeath.participants.actions;

import stardeath.participants.Participant;
import stardeath.world.Floor;
import stardeath.world.Tile;

public class Unveil implements Action {

  private final Floor floor;
  private final int range;

  public Unveil(Floor floor, int range) {
    this.floor = floor;
    this.range = range;
  }

  @Override
  public void execute(Participant participant) {
    floor.getTiles().stream()
        .filter(tile -> tile.distanceTo(participant.getX(), participant.getY()) < this.range)
        .forEach(Tile::unveil);
  }
}
