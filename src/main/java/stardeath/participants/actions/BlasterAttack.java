package stardeath.participants.actions;

import java.util.Optional;
import stardeath.participants.Participant;
import stardeath.world.Floor;

public class BlasterAttack implements Action {

  private final int dx;
  private final int dy;
  private final int range;
  private final int damage;
  private int x;
  private int y;

  // TODO : use Movement after merge
  public BlasterAttack(int x, int y, int dx, int dy, int range, int damage) {
    this.x = x;
    this.y = y;
    this.dx = dx;
    this.dy = dy;
    this.range = range;
    this.damage = damage;
  }

  @Override
  public void execute(Floor level) {
    Optional<Participant> target = Optional.empty();

  }

}
