package stardeath.participants.player;

import stardeath.animates.Animate;
import stardeath.animates.AnimateVisitor;
import stardeath.participants.Participant;
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
  public boolean isVisible() {
    return true;
  }

  @Override
  public void accept(AnimateVisitor visitor) {
    visitor.visitParticipant(this);
  }

  public static class MaskAllParticipants implements Action {

    @Override
    public void execute(Floor level) {
      level.getParticipants().forEach(Animate::hide);
    }
  }

  public class ShowCloseParticipants implements Action {

    @Override
    public void execute(Floor level) {
      RayCasting.compute(Player.this,
          (x, y) -> level.tileAt(x, y).isOpaque(),
          (x, y) -> level.getParticipant(x, y).ifPresent(Animate::show));
    }
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
