import java.util.Random;

public class Player {
	Team myTeam;
	Random r = new Random(1000);

	public Player(Team myTeam) {
		this.myTeam = myTeam;
	}

	public int chooseMovement(boolean fainted) {
		if (fainted) {
			while (true) {
				int rndm = r.nextInt(3);
				if (!myTeam.getTeam()[rndm].isFainted()) {
					return rndm;
				}
			}
		} else {
			int rndm = r.nextInt(3);
			return rndm + 1;
		}
	}
}
