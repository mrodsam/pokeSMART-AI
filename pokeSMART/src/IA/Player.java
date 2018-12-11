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
		while (true) {
			int rndm = r.nextInt(3);
			if (!myTeam.getTeam()[rndm].isFainted()) {
				return rndm;
			}
		}
	}

	public int chooseMovement(State state, int reward) {
		System.out.println(state.getStateName());
		int newAction = la.vGetNewActionAutomata(state.getStateName(), 4, reward);
		System.out.println("Action: " + newAction);

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
		return 0;
	}

	public Team getTeam() {
		return myTeam;
	}

	public void setTeam(Team myTeam) {
		this.myTeam = myTeam;
	}

}
