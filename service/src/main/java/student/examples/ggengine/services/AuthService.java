package student.examples.ggengine.services;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import student.examples.ggengine.domain.entity.User;
import student.examples.ggengine.domain.repository.UserRepository;
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
	@Autowired
	private UserRepository userRepository;

	public User registerNewUserAccount(User user) {
		user.setId(UUID.randomUUID());
		user.setPassword(hashPassword(user.getPassword()));
		user.setToken(hashPassword(user.getId().toString()));

		return userRepository.save(user);
	}

	public String signinUserAccount(User user) {
		Optional<User> userFromDb = userRepository.findByUserNameAndPassword(user.getUserName(),
				hashPassword(user.getPassword()));
		gameService.addParticipant(new Player(userFromDb.get().getId(), userFromDb.get().getUserName()));
		userPublisher.publishUserStatusChange(user.getId(), UserStatus.SIGNIN);

		if (userFromDb.isPresent()) {
			return hashPassword(userFromDb.get().getId().toString());
		}

		return user.getToken();
	}

	private String hashPassword(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
}
