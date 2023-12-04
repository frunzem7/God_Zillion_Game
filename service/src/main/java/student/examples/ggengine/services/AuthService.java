package student.examples.ggengine.services;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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

//	public AuthService() {
//
//	}
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public User registerNewUserAccount(User user) {
		user.setId(UUID.randomUUID());
		user.setPassword(hashPassword(user.getPassword()));
		user.setToken(hashPassword(user.getId().toString()));

		return userRepository.save(user);
	}

	public String signinUserAccount(String userName, String userPassword) {
		User user = new User();
		user.setId(UUID.randomUUID());
		user.setEmail("frunzem7@gmail.com");
		user.setUserName("frunzem");
		user.setPassword(hashPassword("Mariana2"));
		userRepository.save(user);

		Optional<User> userFromDb = userRepository.findByUserNameAndPassword(userName, hashPassword(userPassword));
		gameService.addParticipant(new Player(user.getId(), userName));
		userPublisher.publishUserStatusChange(user.getId(), UserStatus.SIGNIN);

		if (userFromDb.isPresent()) {
			return hashPassword(userFromDb.get().getId().toString());
		}

		return null;
	}

//	public String signIn(String userName, String userPassword) {
//		log.info("Attempting to sign in user: {}", userName);
//
//		Optional<User> user = userRepository.findByUserName(userName);
//
//		if (user.isPresent() && EncryptionUtils.verifyPassword(userPassword, user.get().getPassword())) {
//
//			UUID playerId = user.get().getId();
//			gameService.addParticipant(new Player(playerId, userName));
//			userPublisher.publishUserStatusChange(playerId, UserStatus.SIGNIN);
//			String token = EncryptionUtils.hashPassword(playerId.toString());
//
//			System.out.println(playerId);
//			log.info("User signed in successfully: {}", userName);
//			log.debug("User ID: {}", playerId);
//
//			return token;
//		} else {
//			log.error("Sign in failed for user: {}", userName);
//
//			throw new IllegalArgumentException("Incorrect username or password");
//		}
//	}
//
//	public void signOut(UUID playerId) {
//		log.info("Attempting to sign out user with ID: {}", playerId);
//
//		if (gameService.isParticipantExist(playerId)) {
//			gameService.removeParticipant(playerId);
//			userPublisher.publishUserStatusChange(playerId, UserStatus.SIGNOUT);
//
//			log.info("User signed out successfully");
//		} else {
//			log.error("Sign out failed for user with ID: {}", playerId);
//
//			throw new IllegalArgumentException("Participant not found for sign-out.");
//		}
//	}

	private String hashPassword(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
}
