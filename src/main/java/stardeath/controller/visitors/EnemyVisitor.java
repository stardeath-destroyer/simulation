package stardeath.controller.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import stardeath.animates.participants.Faction;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.visitors.NoOpAnimateVisitor;

public class EnemyVisitor extends NoOpAnimateVisitor {

  private final Faction faction;
  private Player player;
  private List<Participant> enemies;
  private List<Participant> friends;

  public EnemyVisitor(Faction faction) {
    this.faction = faction;
    this.enemies = new ArrayList<>();
    this.friends = new ArrayList<>();
  }

  @Override
  public void visitParticipant(Player player) {
    this.player = player;
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    if (trooper.getFaction().equals(faction)) {
      friends.add(trooper);
    } else {
      enemies.add(trooper);
    }
  }

  public Optional<Player> getPlayerPos() {
    return Optional.ofNullable(player);
  }

  public List<Participant> getEnemies() {
    return Collections.unmodifiableList(enemies);
  }

  public List<Participant> getFriends() {
    return Collections.unmodifiableList(friends);
  }
}
