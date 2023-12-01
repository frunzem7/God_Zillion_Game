package student.examples.ggengine.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.events.GamePublisher;
import student.examples.ggengine.game.GameState;
import student.examples.ggengine.game.MultiPlayerTeamGame;

@Slf4j
@Component
@RequiredArgsConstructor
public class MultiplayerTeamGameFactory implements GameFactory {
	private final GamePublisher gamePublisher;

	@Override
	public MultiPlayerTeamGame createGame() {
		UUID id = UUID.randomUUID();
		gamePublisher.publishGameStatusChange(id, GameState.STARTED);

		log.info("Multiplayer Team Game Created");

		return new MultiPlayerTeamGame();
	}
}
