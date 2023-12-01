package student.examples.ggengine.game;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;

@Getter
public abstract class Game {
	private UUID id;
	private Set<Item> items = new HashSet<>();
	private GameState gameState = GameState.PENDING;
//	private Set<Player> players;

}
