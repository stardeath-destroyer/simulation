package stardeath.controller.visitors;

import java.util.List;
import java.util.Optional;
import stardeath.animates.participants.Faction;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.visitors.NoOpAnimateVisitor;

public class EnnemyVisitor extends NoOpAnimateVisitor {

  private final Faction friend;
  private Player player;
  private List<Participant> enemies;

  public EnnemyVisitor(Faction friend) {
    this.friend = friend;
  }

  @Override
  public void visitParticipant(Player player) {
    this.player = player;
  }

  public Optional<Player> getPlayerPos() {
    return Optional.ofNullable(player);
  }
}
