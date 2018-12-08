import java.util.Random;

public class Player {
	Team myTeam;
	Random r = new Random(1000);
	LearningAutomata la;

	public Player(Team myTeam) {
		this.la = new LearningAutomata();
		this.myTeam = myTeam;
	}

	public int chooseMovement(boolean fainted, State state, int reward) {
		if (fainted) {
			while (true) {
				int rndm = r.nextInt(3);
				if (!myTeam.getTeam()[rndm].isFainted()) {
					return rndm;
				}
			}
		} else {
//			int rndm = r.nextInt(3);
//			return rndm + 1;
			System.out.println(state.toString());
			int newAction = la.vGetNewActionAutomata(state.getStateName(), state.getNumActions(), reward);
			System.out.println("Action: " + newAction);
			switch (state.getNumActions()) {
			// Si quiere cambiar a backup1 = 10 y si quiere a backup2=11
			case 4:
				switch (newAction) {
				case 0:
					/*Cambiar a backup1*/
					return 10;
				case 1:
					/*Cambiar a backup2*/
					return 11;
				case 2:
					/*Ataque neutro*/
					return 2;
				case 3:
					/*Ataque de tipo*/
					return 3;
				}
			case 3:
				switch (newAction) {
				case 0:
					return 2;
				case 1:
					return 3;
				case 2:
					return 12;
				}
			case 2:
				return newAction + 2;
			}
		}
		return 0;
	}
}
