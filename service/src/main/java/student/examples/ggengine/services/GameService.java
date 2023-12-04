package student.examples.ggengine.services;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.events.UserPublisher;
import student.examples.ggengine.factory.MultiplayerTeamGameFactory;
import student.examples.ggengine.factory.PlayerFactory;
import student.examples.ggengine.game.MultiPlayerTeamGame;
import student.examples.ggengine.game.Participant;
import student.examples.ggengine.game.Team;

@Slf4j
@Getter
@Setter
@Service
public class GameService {
	@Autowired
	private UserPublisher userPublisher;
	@Autowired
	private MultiplayerTeamGameFactory multiplayerTeamGameFactory;
	@Autowired
	private PlayerFactory playerFactory;

	private Set<MultiPlayerTeamGame> games;
	private Set<Participant> allParticipants;

	public GameService() {
		init();
	}

	public void init() {
		games = new HashSet<>();
		allParticipants = new HashSet<>();
	}

	public void addParticipant(Participant participant) {
		allParticipants.add(participant);
		System.out.println("This is participants size: " + allParticipants.size());
	}

	public void removeParticipant(UUID playerId) {
		Participant participantToRemove = allParticipants.stream()
				.filter(participant -> participant.getId().equals(playerId)).findFirst().orElse(null);

		if (participantToRemove != null) {
			allParticipants.remove(participantToRemove);
		}
	}

	public boolean isParticipantExist(UUID playerId) {
		return allParticipants.stream().anyMatch(participant -> participant.getId().equals(playerId));
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

	public ResponseEntity<String> joinGame(UUID playerId) {
		// Search for the player in the waiting area
		Participant player = allParticipants.stream().filter(participant -> participant.getId().equals(playerId))
				.findFirst().orElse(null);

		if (player != null) {
			// Check if there's an existing game and if Team A is not full
			if (!games.isEmpty()
					&& games.stream().anyMatch(game -> game.getTeams().get("TEAM A").getParticipants().size() < 4)) {
				// Add the player to Team A of the existing game
				MultiPlayerTeamGame existingGame = games.stream().findFirst().get();
				existingGame.getTeams().get("TEAM A").add(player);

				log.info("Player joined existing game: " + existingGame.toString());
				return ResponseEntity.ok("Player joined existing game successfully. Game ID: " + existingGame.getId());
			} else {
				// Create a new game and add the player to Team A
				MultiPlayerTeamGame newGame = multiplayerTeamGameFactory.createGame();
				Team teamA = new Team();
				teamA.add(player);
				newGame.getTeams().put("TEAM A", teamA);
				games.add(newGame);

				log.info("Player joined new game: " + newGame.toString());
				return ResponseEntity.ok("Player joined new game successfully. Game ID: " + newGame.getId());
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("{\"status\": \"failed\", \"message\": \"Unauthorized game access.\"}");
		}
	}

	public ResponseEntity<String> leaveGame(UUID playerId) {
		// Search for the player in the games
		Participant player = allParticipants.stream().filter(participant -> participant.getId().equals(playerId))
				.findFirst().orElse(null);

		if (player != null) {
			// Remove the player from all games
			games.forEach(game -> {
				game.getTeams().forEach((teamName, team) -> team.remove(player));
			});

			// Remove the player from the waiting area
			removeParticipant(playerId);

			log.info("Player left all games: " + player.toString());
			return ResponseEntity.ok("Player left all games successfully.");
		} else {
			throw new IllegalArgumentException("Player not found in any game.");
		}
	}
}
