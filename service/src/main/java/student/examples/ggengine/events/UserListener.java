package student.examples.ggengine.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserListener implements ApplicationListener<UserEvent> {

	@Override
	public void onApplicationEvent(UserEvent event) {
		log.info("Received custom event: " + event.getUserStatus());
	}
}
