package student.examples.ggengine.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.events.GameEventListener;
import student.examples.ggengine.game.Game;
import student.examples.ggengine.game.GameState;
import student.examples.ggengine.game.Item;
import student.examples.ggengine.game.Rock;

@Slf4j
@Getter
@Service
public class GameService {
//	private Set<Item> items;
	private Set<Game> games;

	public GameService() {
		init();
	}

	public void init() {
		games = new HashSet<>();
		games.add(new Game());

		Game game = games.stream().findFirst().get();
		game.getItems().add(new Rock(0, 0, 0, 0, 0, 0, 0, 0, null));
	}

	@Scheduled(fixedRate = 1000) // 15
	public void updateFrame() {
		Game game = games.stream().findFirst().get();
		Item item = game.getItems().stream().findFirst().get();

		game.getItems().remove(item);
		game.getItems().add(new Rock(item.getWidth(), item.getHeight(), item.getSpeedX(), item.getSpeedY(),
				item.getTop() + 1, item.getLeft(), item.getRotation(), item.getRotationSpeed(), item.getItemType()));
		log.info("Update:" + item.getTop());
		log.info("Current game state: " + game);
	}

	public void addGame() {
		Game newGame = new Game();
		newGame.getItems().add(new Rock(0, 0, 0, 0, 0, 0, 0, 0, null));
		newGame.setGameState(GameState.STARTED);
		games.add(newGame);

		log.info("Added a new game!");
		log.info("All games:");
		games.forEach(g -> log.info(g.toString()));
	}
}
