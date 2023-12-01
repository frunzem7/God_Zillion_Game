package student.examples.ggengine.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GameListener implements ApplicationListener<GameEvent> {

	@Override
	public void onApplicationEvent(GameEvent event) {
		log.info("Received custom event: " + event.getGameId());
		log.info("Received custom event: " + event.getNewStatus());
	}
}
