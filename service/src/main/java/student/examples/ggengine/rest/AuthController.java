package student.examples.ggengine.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import student.examples.ggengine.domain.entity.User;
import student.examples.ggengine.services.AuthService;
import student.examples.ggengine.services.GameService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthService authService;
	@Autowired
	GameService gameService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody User user) {
		return new ResponseEntity<>(authService.registerNewUserAccount(user), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody User user) {
		try {

			String token = authService.signinUserAccount(user);
			return ResponseEntity.ok("{\"status\": \"success\", \"token\": \"" + token + "\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"status\": \"failed\", \"message\": \"An error occurred during sign-in.\"}");
		}
	}

	@GetMapping("/signout/{playerId}")
	public ResponseEntity<String> signOut(@PathVariable UUID playerId) {
		try {
			gameService.leaveGame(playerId);
			return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Signed out successfully.\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"status\": \"failed\", \"message\": \"An error occurred during sign-out.\"}");
		}
	}
}
