package student.examples.ggengine.game;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
	private GameState gameState = GameState.PENDING;
	private Set<Item> items = new HashSet<>();
//	private Set<Player> players;

}
