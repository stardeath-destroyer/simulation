package stardeath.rendering;

import stardeath.participants.ParticipantVisitor;
import stardeath.world.TileVisitor;

public interface Renderer extends TileVisitor, ParticipantVisitor {

  void clear();

  void render();
}
