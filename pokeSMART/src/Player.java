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
			System.out.println(state.getStateName());
			int newAction = la.vGetNewActionAutomata(state.getStateName(), state.getNumActions(), reward);
			System.out.println("Action: " + newAction);
			switch (state.getNumActions()) {
			case 4: //Ninguno de mis Pokémon está desmayado
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
			case 3: //Alguno de mis Pokémon está desmayado
				switch (newAction) {
				case 0:
					/*Ataque neutro*/
					return 2;
				case 1:
					/*Ataque de tipo*/
					return 3;
				case 2:
					/*Cambiar al backup no desmayado*/
					return 12;
				}
			case 2: //2 de mis Pokémon están desmayados
				/*Ataque neutro o de tipo*/
				return newAction + 2;
			}
		}
		return 0;
	}

	public Team getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(Team myTeam) {
		this.myTeam = myTeam;
	}
	
	
}
