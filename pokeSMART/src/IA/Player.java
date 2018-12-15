package IA;

import java.util.Random;

import pokemon.Team;

public class Player {
	Team myTeam;
	Random r = new Random(1000);
	public LearningAutomata la;

	public Player(Team myTeam, int id) {
		this.la = new LearningAutomata(id);
		this.myTeam = myTeam;
	}

	public int choosePokemonRandomly() {
		for (int i = 0; i < 3; i++) {
			if (!myTeam.getTeam()[i].isFainted()) {
				return i;
			}
		}
		return 10;
	}

	public int chooseMovement(State state, int reward) {
		while (true) {
			int newAction = la.vGetNewActionAutomata(state.getStateName(), 4, reward);

			if (newAction == 0 && myTeam.getBackup1().isFainted()) {
				reward = -100;
				continue;
			}

			if (newAction == 1 && myTeam.getBackup2().isFainted()) {
				reward = -100;
				continue;
			}

			switch (newAction) {
			case 0:
				/* Cambiar a backup1 */
				return 0;
			case 1:
				/* Cambiar a backup2 */
				return 1;
			case 2:
				/* Ataque neutro */
				return 2;
			case 3:
				/* Ataque de tipo */
				return 3;
			}
		}
	}

	public Team getTeam() {
		return myTeam;
	}

	public void setTeam(Team myTeam) {
		this.myTeam = myTeam;
	}

}
