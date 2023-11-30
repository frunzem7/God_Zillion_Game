package student.examples.ggengine.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import student.examples.ggengine.game.GameState;

@Getter
public class GameStatusChangeEvent extends ApplicationEvent {
	private final Long gameId;
	private final GameState newStatus;

	public GameStatusChangeEvent(Object source, Long gameId, GameState newStatus) {
		super(source);
		this.gameId = gameId;
		this.newStatus = newStatus;
	}
}