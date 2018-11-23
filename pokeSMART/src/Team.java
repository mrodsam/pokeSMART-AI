import java.util.Arrays;

public class Team {
	private Pokemon[] team;
	private Pokemon currentPokemon;

	public Team(Pokemon[] team) {
		this.team = team;
		this.currentPokemon = team[0];
	}

	public Pokemon[] getTeam() {
		return team;
	}

	public void setTeam(Pokemon[] team) {
		this.team = team;
	}

	public Pokemon getCurrentPokemon() {
		return currentPokemon;
	}

	public void setCurrentPokemon(Pokemon currentPokemon) {
		this.currentPokemon = currentPokemon;
	}

	@Override
	public String toString() {
		return "Team [team=" + Arrays.toString(team) + ", currentPokemon=" + currentPokemon.getName() + "]";
	}

}
