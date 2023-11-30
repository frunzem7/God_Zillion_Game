package student.examples.ggengine.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.services.GameService;

@Slf4j
@Component
public class GameEventListener implements ApplicationListener<GameStatusChangeEvent> {
	@Autowired
	private GameService gameService;

	@Override
	public void onApplicationEvent(GameStatusChangeEvent event) {
		log.info("Received custom event: " + event.getGameId());
		log.info("Received custom event: " + event.getNewStatus());
		gameService.addGame();
	}

}
