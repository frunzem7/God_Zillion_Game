package student.examples.ggengine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.services.GameService;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
	@Autowired
	GameService gameService;

	@GetMapping("/join/{id}")
	public Long joinGame(@PathVariable Long id) {
		gameService.joinGame();
		log.info("id: " + id);
		return id;
	}

	@GetMapping("/leave/{id}")
	public Long leaveGame(@PathVariable Long id) {
		return id;
	}
}
