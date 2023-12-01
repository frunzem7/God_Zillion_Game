package student.examples.ggengine.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import student.examples.ggengine.game.Player;

@Component
public class PlayerFactory implements ParticipantFactory {

	@Override
	public Player createParticipant() {
		return new Player(UUID.randomUUID(), "name");
	}
}
