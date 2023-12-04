package student.examples.ggengine.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.domain.entity.EncryptionUtils;
import student.examples.ggengine.domain.entity.User;
import student.examples.ggengine.domain.repository.UserRepository;
import student.examples.ggengine.events.UserPublisher;
import student.examples.ggengine.game.Player;
import student.examples.ggengine.game.UserStatus;

@Slf4j
@Getter
@Setter
@Service
public class AuthService {
	@Autowired
	private UserPublisher userPublisher;
	@Autowired
	private GameService gameService;
	@Autowired
	private UserRepository userRepository;

	public AuthService() {

	}

	public String signIn(String userName, String userPassword) {
		log.info("Attempting to sign in user: {}", userName);

		Optional<User> user = userRepository.findByUserName(userName);

		if (user.isPresent() && EncryptionUtils.verifyPassword(userPassword, user.get().getPassword())) {

			UUID playerId = user.get().getId();
			gameService.addParticipant(new Player(playerId, userName));
			userPublisher.publishUserStatusChange(playerId, UserStatus.SIGNIN);
			String token = EncryptionUtils.hashPassword(playerId.toString());

			System.out.println(playerId);
			log.info("User signed in successfully: {}", userName);
			log.debug("User ID: {}", playerId);

			return token;
		} else {
			log.error("Sign in failed for user: {}", userName);

			throw new IllegalArgumentException("Incorrect username or password");
		}
	}

	public void signOut(UUID playerId) {
		log.info("Attempting to sign out user with ID: {}", playerId);

		if (gameService.isParticipantExist(playerId)) {
			gameService.removeParticipant(playerId);
			userPublisher.publishUserStatusChange(playerId, UserStatus.SIGNOUT);

			log.info("User signed out successfully");
		} else {
			log.error("Sign out failed for user with ID: {}", playerId);

			throw new IllegalArgumentException("Participant not found for sign-out.");
		}
	}
}
