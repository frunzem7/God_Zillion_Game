package student.examples.ggengine.events;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import student.examples.ggengine.game.GameState;

@Component
public class GamePublisher {
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void publishGameStatusChange(UUID gameId, GameState newStatus) {
		GameEvent event = new GameEvent(this, gameId, newStatus);
		eventPublisher.publishEvent(event);
	}
}
