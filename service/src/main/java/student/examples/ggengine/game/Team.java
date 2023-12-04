package student.examples.ggengine.game;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Team implements ParticipantCollection {
	private Set<Participant> participants = new HashSet<Participant>();

	@Override
	public void add(Participant participant) {
		participants.add(participant);
	}

	@Override
	public void remove(Participant participant) {
		participants.remove(participant);
	}
}
