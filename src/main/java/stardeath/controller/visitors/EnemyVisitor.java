package stardeath.controller.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import stardeath.animates.participants.Faction;
import stardeath.animates.participants.Participant;
import stardeath.animates.participants.entities.FlameTrooper;
import stardeath.animates.participants.entities.JumpTrooper;
import stardeath.animates.participants.entities.Player;
import stardeath.animates.participants.entities.Soldier;
import stardeath.animates.participants.entities.Trooper;
import stardeath.animates.participants.entities.Wookie;
import stardeath.animates.visitors.NoOpAnimateVisitor;

/**
 * This visitor will visit animates and classify them according to their faction
 */
public class EnemyVisitor extends NoOpAnimateVisitor {

  private final Faction faction;
  private Player player;
  private final List<Participant> enemies;
  private final List<Participant> friends;

  public EnemyVisitor(Faction faction) {
    this.faction = faction;
    this.enemies = new ArrayList<>();
    this.friends = new ArrayList<>();
  }

  private void defaultEnemyVerification(Participant p) {
    if (p.getFaction().equals(faction)) {
      friends.add(p);
    } else {
      enemies.add(p);
    }
  }

  @Override
  public void visitParticipant(Player player) {
    this.player = player;
    defaultEnemyVerification(player);
  }

  @Override
  public void visitParticipant(JumpTrooper trooper) {
    defaultEnemyVerification(trooper);
  }

  @Override
  public void visitParticipant(FlameTrooper trooper) {
    defaultEnemyVerification(trooper);
  }

  @Override
  public void visitParticipant(Trooper trooper) {
    defaultEnemyVerification(trooper);
  }

  @Override
  public void visitParticipant(Soldier soldier) {
    defaultEnemyVerification(soldier);
  }

  @Override
  public void visitParticipant(Wookie wookie) {
    defaultEnemyVerification(wookie);
  }

  /**
   * Get the player if he was found
   * @return An optional player or empty if the player was not found
   */
  public Optional<Player> getPlayer() {
    return Optional.ofNullable(player);
  }

  /**
   * Get the list of enemies that were visited.
   * @return An unmodifiable list of enemies
   */
  public List<Participant> getEnemies() {
    return Collections.unmodifiableList(enemies);
  }

  /**
   * Get the list of friends that were visited.
   * @return An unmodifiable list of friends
   */
  public List<Participant> getFriends() {
    return Collections.unmodifiableList(friends);
  }
}
