package student.examples.ggengine.game;

import java.util.UUID;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Player implements Participant {
	private UUID id;
	private String name;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

}
