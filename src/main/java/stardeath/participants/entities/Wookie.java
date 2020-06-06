package stardeath.participants.entities;

import stardeath.participants.Participant;
import stardeath.participants.ParticipantVisitor;
import stardeath.participants.attacks.SplashAttacker;

public class Wookie extends Participant implements SplashAttacker {

  @Override
  public void accept(ParticipantVisitor visitor) {
    visitor.visitParticipant(this);
  }
}
