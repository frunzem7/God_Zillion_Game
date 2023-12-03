package student.examples.ggengine.events;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import student.examples.ggengine.game.UserStatus;

@Getter
public class UserEvent extends ApplicationEvent {
	private final UUID playerId;
	private final UserStatus userStatus;

	public UserEvent(Object source, UUID playerId, UserStatus userStatus) {
		super(source);
		this.playerId = playerId;
		this.userStatus = userStatus;
	}
}
