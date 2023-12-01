package student.examples.ggengine.game;

import java.util.Set;
import java.util.UUID;

public abstract class Game {
	public abstract UUID getId();

	public abstract Set<Item> getItems();

}
