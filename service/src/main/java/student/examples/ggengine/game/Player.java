package student.examples.ggengine.game;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player implements Participant {
	private UUID id;
	private String name;

}
