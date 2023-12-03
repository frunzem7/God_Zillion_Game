package student.examples.ggengine.events;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import student.examples.ggengine.game.UserStatus;

@Component
public class UserPublisher {
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void publishUserStatusChange(UUID playerId, UserStatus newStatus) {
		UserEvent event = new UserEvent(this, playerId, newStatus);
		eventPublisher.publishEvent(event);
	}
}
