package student.examples.ggengine.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.factory.MultiplayerTeamGameFactory;
import student.examples.ggengine.factory.PlayerFactory;
import student.examples.ggengine.game.MultiPlayerTeamGame;
import student.examples.ggengine.game.Player;
import student.examples.ggengine.game.Team;

@Slf4j
@Getter
@Setter
@Service
public class GameService {
	@Autowired
	private MultiplayerTeamGameFactory multiplayerTeamGameFactory;

	@Autowired
	private PlayerFactory playerFactory;

	private Set<MultiPlayerTeamGame> games;

	public GameService() {
		init();
	}

	public void init() {
		games = new HashSet<>();
	}

	@Scheduled(fixedRate = 1000) // 15
	public void updateFrame() {
//		if (games.isEmpty()) {
//			return;
//		}
//
//		Game game = games.stream().findFirst().get();
//		Item item = game.getItems().stream().findFirst().get();
//
//		game.getItems().remove(item);
//		game.getItems().add(new Rock(item.getWidth(), item.getHeight(), item.getSpeedX(), item.getSpeedY(),
//				item.getTop() + 1, item.getLeft(), item.getRotation(), item.getRotationSpeed(), item.getItemType()));
//
//		log.info("Update:" + item.getTop());
//		log.info("Current game state: " + game);
	}

	public void joinGame() {
		Player player = playerFactory.createParticipant();
		Team team = new Team();
		team.add(player);
		
		if (games.isEmpty()) {
			MultiPlayerTeamGame newGame = multiplayerTeamGameFactory.createGame();
			newGame.getTeams().put("TEAM A", team);
			games.add(newGame);
		}

		games.stream().findFirst().get().getTeams().get("TEAM A").add(player);

		log.info(games.stream().findFirst().get().toString());
	}
}
