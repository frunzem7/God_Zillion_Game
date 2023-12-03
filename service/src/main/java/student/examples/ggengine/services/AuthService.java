package student.examples.ggengine.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import student.examples.ggengine.events.UserPublisher;
import student.examples.ggengine.game.Player;
import student.examples.ggengine.game.UserStatus;

@Getter
@Setter
@Service
public class AuthService {
	@Autowired
	private UserPublisher userPublisher;
	@Autowired
	private GameService gameService;

	public AuthService() {

	}

	public void signIn(String userName, String userPassword) {
		if ("user".equals(userName) && "user".equals(userPassword)) {
			UUID playerId = UUID.randomUUID();
			gameService.addParticipant(new Player(playerId, userName));
			userPublisher.publishUserStatusChange(playerId, UserStatus.SIGNIN);
			System.out.println(playerId);
		} else {
			throw new IllegalArgumentException("Incorrect username or password");
		}
	}

	public void signOut(UUID playerId) {
		if (gameService.isParticipantExist(playerId)) {
			gameService.removeParticipant(playerId);
			userPublisher.publishUserStatusChange(playerId, UserStatus.SIGNOUT);
		} else {
			throw new IllegalArgumentException("Participant not found for sign-out.");
		}
	}
}
