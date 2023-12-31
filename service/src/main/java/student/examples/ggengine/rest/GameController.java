package student.examples.ggengine.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import student.examples.ggengine.services.GameService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
	@Autowired
	GameService gameService;

	@PostMapping("/join/{playerId}")
	public ResponseEntity<String> joinGame(@PathVariable UUID playerId) {
		try {
			gameService.joinGame(playerId);
			return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Join in game successfully.\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"status\": \"failed\", \"message\": \"An error occurred during game join.\"}");
		}
	}

	@GetMapping("/leave/{playerId}")
	public ResponseEntity<String> leaveGame(@PathVariable UUID playerId) {
		try {
			gameService.leaveGame(playerId);
			return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Leave game successfully.\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"status\": \"failed\", \"message\": \"An error occurred during leave game.\"}");
		}
	}
}
