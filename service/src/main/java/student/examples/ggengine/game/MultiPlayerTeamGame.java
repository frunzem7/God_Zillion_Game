package student.examples.ggengine.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MultiPlayerTeamGame extends Game implements HasTeams, HasPlayers {

	private UUID id;
	private Set<Item> items;
	private Map<String, Team> teams;
	private Set<Participant> participants;

	public MultiPlayerTeamGame() {
		this.teams = new HashMap<>();
	}

	@Override
	public Set<Participant> getPlayers() {
		return participants;
	}

	@Override
	public Map<String, Team> getTeams() {
		return teams;
	}
}
