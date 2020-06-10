package stardeath.participants.player;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.actions.Action;
import stardeath.world.Floor;
import stardeath.world.Tile;

public class Player extends Participant {

  private static final int DEFAULT_VISIBILITY_RANGE = 20;

  private int visibilityRange;

  public Player(int x, int y) {
    super(x, y);
    this.visibilityRange = DEFAULT_VISIBILITY_RANGE;
  }

  public int getVisibilityRange() {
    return this.visibilityRange;
  }

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }

  public class UnveilAction implements Action {

    private final Floor floor;

    public UnveilAction(Floor floor) {
      this.floor = floor;
    }

    @Override
    public void execute(Floor level) {
      floor.getTiles().stream()
          .filter(tile -> tile.distanceTo(getX(), getY()) < getVisibilityRange())
          .forEach(Tile::unveil);
    }
  }
}
