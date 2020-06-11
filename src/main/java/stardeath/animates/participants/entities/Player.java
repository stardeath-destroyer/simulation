package stardeath.animates.participants.entities;

import stardeath.animates.Animate;
import stardeath.animates.visitors.AnimateVisitor;
import stardeath.animates.actions.Action;
import stardeath.animates.participants.Faction;
import stardeath.world.Floor;
import stardeath.world.visibility.RayCasting;

public class Player extends Soldier {

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
