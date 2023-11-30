package student.examples.ggengine.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import student.examples.ggengine.game.GameState;

@Component
public class GameEventPublisher {
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void publishGameStatusChange(Long gameId, GameState newStatus) {
		GameStatusChangeEvent event = new GameStatusChangeEvent(this, gameId, newStatus);
		eventPublisher.publishEvent(event);
	}
}
