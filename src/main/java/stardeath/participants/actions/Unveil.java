package stardeath.participants.actions;

import stardeath.participants.Participant;
import stardeath.participants.player.Player;
import stardeath.world.Floor;
import stardeath.world.Tile;

public class Unveil implements Action {

  private final Floor floor;

  public Unveil(Floor floor) {
    this.floor = floor;
  }

  @Override
  public void execute(Participant participant) {
    Player player = (Player) participant;
    floor.getTiles().stream()
        .filter(tile -> tile.distanceTo(player.getX(), player.getY()) < player.getVisibilityRange())
        .forEach(Tile::unveil);
  }
}
