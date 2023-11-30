package student.examples.ggengine.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import student.examples.ggengine.conn.Frame;
import student.examples.ggengine.services.GameService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class FrameController {
	private final GameService gameService;

	@PostMapping("update")
	public Frame getData() {
		Frame frame = new Frame(0, gameService.getGames().stream().findFirst().get().getItems());
		return frame;
	}
}
