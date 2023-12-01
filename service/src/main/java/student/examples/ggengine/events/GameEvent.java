package student.examples.ggengine.events;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import student.examples.ggengine.game.GameState;

@Getter
public class GameEvent extends ApplicationEvent {
	private final UUID gameId;
	private final GameState newStatus;

	public GameEvent(Object source, UUID gameId, GameState newStatus) {
		super(source);
		this.gameId = gameId;
		this.newStatus = newStatus;
	}
}