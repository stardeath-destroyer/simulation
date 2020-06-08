package stardeath.rendering;

import java.util.Collection;
import stardeath.participants.Participant;
import stardeath.world.Floor;

public interface Renderer {

  void render(Floor floor, Collection<Participant> players);
}
