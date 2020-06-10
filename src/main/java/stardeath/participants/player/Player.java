package stardeath.participants.player;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.actions.Action;
import stardeath.participants.factions.Faction;
import stardeath.world.Floor;
import stardeath.world.visibility.RayCasting;

public class Player extends Participant {

  private static final int DEFAULT_VISIBILITY_RANGE = 13;

  private int visibilityRange;

  public Player(int x, int y) {
    super(x, y, Faction.Rebels, 100);
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

    @Override
    public void execute(Floor level) {
      RayCasting.compute(Player.this,
          (x, y) -> level.tileAt(x, y).isOpaque(),
          (x, y) -> level.tileAt(x, y).unveil());
    }
  }
}
