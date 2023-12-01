package student.examples.ggengine.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.factory.GameFactory;
import student.examples.ggengine.game.Game;
import student.examples.ggengine.game.Item;
import student.examples.ggengine.game.Rock;

@Slf4j
@Getter
@Setter
@Service
public class GameService {
	@Autowired
	private GameFactory gameFactory;
	private Set<Game> games;

	public GameService() {
		init();
	}

	public void init() {
		games = new HashSet<>();
	}

	@Scheduled(fixedRate = 1000) // 15
	public void updateFrame() {
		if (games.isEmpty()) {
			return;
		}

		Game game = games.stream().findFirst().get();
		Item item = game.getItems().stream().findFirst().get();

		game.getItems().remove(item);
		game.getItems().add(new Rock(item.getWidth(), item.getHeight(), item.getSpeedX(), item.getSpeedY(),
				item.getTop() + 1, item.getLeft(), item.getRotation(), item.getRotationSpeed(), item.getItemType()));

		log.info("Update:" + item.getTop());
		log.info("Current game state: " + game);
	}

	public void joinGame(Long id) {
		if (games.isEmpty()) {
			// Ask the factory to create a game
			Game newGame = gameFactory.createGame();
			Rock rock = new Rock(0, 0, 0, 0, 0, 0, 0, 0, null);
			newGame.getItems().add(rock);

			// Add the default game object to the collection
			games.add(newGame);
		}
	}
}
