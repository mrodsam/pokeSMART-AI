package pokemon;
import java.util.Arrays;

public class Team {
	private Pokemon[] team;
	public int currentPokemon;
	public int backup1;
	public int backup2;

	public Team(Pokemon[] team) {
		this.team = team;
		this.currentPokemon = 0;
		this.backup1 = 1;
		this.backup2 = 2;
	}

	public Pokemon[] getTeam() {
		return team;
	}

	public void setTeam(Pokemon[] team) {
		this.team = team;
	}

	public Pokemon getCurrentPokemon() {
		return team[currentPokemon];
	}

	public void setCurrentPokemon(int currentPokemon) {
		this.currentPokemon = currentPokemon;
		setBackups();
	}

	public Pokemon getBackup1() {
		return team[backup1];
	}

	public Pokemon getBackup2() {
		return team[backup2];
	}

	public void setBackups() {
		switch (currentPokemon) {
		case 0:
			backup1 = 1;
			backup2 = 2;
		case 1:
			backup1 = 0;
			backup2 = 2;
		case 2:
			backup1 = 0;
			backup2 = 1;
		}
	}

	public int getFaintedPokemon() {
		int count = 0;
		for (int i = 0; i < team.length; i++) {
			if (team[i].isFainted()) {
				count++;
			}
		}
		return count;
	}

	@Override
	public String toString() {
		return "Team [team=" + Arrays.toString(team) + ", currentPokemon=" + team[currentPokemon] + ", backup1="
				+ team[backup1] + ", backup2=" + team[backup2] + "]";
	}

}
