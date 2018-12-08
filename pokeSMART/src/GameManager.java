import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int reward = 0;
		boolean end = false;

		Team rivalTeam = new Team(generateRandomTeam());
//		Team team2 = new Team(generateRandomTeam());

		Pokemon[] pokemonteam2 = new Pokemon[3];
		pokemonteam2[0] = new Pokemon(AvailablePokemon.Poliwag);
		pokemonteam2[1] = new Pokemon(AvailablePokemon.Poochyena);
		pokemonteam2[2] = new Pokemon(AvailablePokemon.Charmander);
		Team myTeam = new Team(pokemonteam2);
		Player randomPlayer = new Player(myTeam);

		System.out.println("Team 1: " + rivalTeam.toString());
		System.out.println("Team 2: " + myTeam.toString());

		System.out.println("Number of players[1 or 2]: ");
		int players = Integer.parseInt(sc.nextLine());

		while (!end) {
			Pokemon rivalBeforeSwitch = rivalTeam.getCurrentPokemon();
			Pokemon myBeforeSwitch = myTeam.getCurrentPokemon();
			int count1 = 0;
			int count2 = 0;
			for (int i = 0; i < rivalTeam.getTeam().length; i++) {
				if (rivalTeam.getTeam()[i].isFainted())
					count1++;
			}
			for (int i = 0; i < myTeam.getTeam().length; i++) {
				if (myTeam.getTeam()[i].isFainted())
					count2++;
			}
			if (count1 == 3 && count2 == 3) {
				System.out.println("Game over");
				end = true;
				break;
			}
			if (count1 == 3) {
				System.out.println("Player AI won the battle.");
				end = true;
				break;
			}
			if (count2 == 3) {
				System.out.println("Player 1 won the battle.");
				end = true;
				break;
			}

			int movement[] = new int[2];
			System.out.println("*********** Battle: " + rivalTeam.getCurrentPokemon().getName() + "["
					+ rivalTeam.getCurrentPokemon().getHp() + "]" + " vs " + myTeam.getCurrentPokemon().getName() + "["
					+ myTeam.getCurrentPokemon().getHp() + "]" + " **********");
			if (rivalTeam.getCurrentPokemon().isFainted()) {
				changePokemon(rivalTeam);
			}
			System.out.println("[" + rivalTeam.getCurrentPokemon() + "] Choose a movement: ");
			System.out.println("1. Switch");
			System.out.println("2. Neutral Attack");
			System.out.println("3. Type Attack");

			movement[0] = Integer.parseInt(sc.nextLine());

			if (movement[0] == 1) {
				changePokemon(rivalTeam);
			}

			switch (players) {
			case 2:
				if (myTeam.getCurrentPokemon().isFainted()) {
					changePokemon(myTeam);
				}

				System.out.println("[" + myTeam.getCurrentPokemon() + "] Choose a movement: ");
				System.out.println("1. Switch");
				System.out.println("2. Neutral Attack");
				System.out.println("3. Type Attack");

				movement[1] = Integer.parseInt(sc.nextLine());

				if (movement[1] == 1) {
					changePokemon(myTeam);
				}
				break;

			case 1:

				if (myTeam.getCurrentPokemon().isFainted()) {
					myTeam.setCurrentPokemon(randomPlayer.chooseMovement(true, null, 0));
					System.out.println("Player AI switch to " + myTeam.getCurrentPokemon());
				}
				State s = new State(myTeam.getCurrentPokemon(), myTeam.getBackup1(), myTeam.getBackup2(),
						rivalTeam.getCurrentPokemon(), rivalTeam.getBackup1(), rivalTeam.getBackup2());
				movement[1] = randomPlayer.chooseMovement(false, s, reward);
				if (movement[1] == 10) {
					myTeam.setCurrentPokemon(myTeam.backup1);
					System.out.println("Player AI switch to " + myTeam.getCurrentPokemon());
				} else if (movement[1] == 11) {
					myTeam.setCurrentPokemon(myTeam.backup2);
					System.out.println("Player AI switch to " + myTeam.getCurrentPokemon());
				}
				if (movement[1] == 12) {
					if (myTeam.getBackup1().isFainted())
						myTeam.setCurrentPokemon(myTeam.backup2);
					else
						myTeam.setCurrentPokemon(myTeam.backup1);
				}
				break;
			}
			int myDamage = 0;
			int rivalDamage = 0;
			for (int i = 0; i < movement.length; i++) {
				switch (movement[i]) {
				case 2:
					if (i == 0) {
						myDamage = attack(rivalTeam.getCurrentPokemon(), myTeam.getCurrentPokemon(), false);
					} else if (i == 1) {
						rivalDamage = attack(myTeam.getCurrentPokemon(), rivalTeam.getCurrentPokemon(), false);
					}
					break;

				case 3:
					if (i == 0) {
						myDamage = attack(rivalTeam.getCurrentPokemon(), myTeam.getCurrentPokemon(), true);
					} else if (i == 1) {
						rivalDamage = attack(myTeam.getCurrentPokemon(), rivalTeam.getCurrentPokemon(), true);
					}
					break;
				case 4:
					end = true;
					break;
				}
			}

			/******************* CALCULAR REWARD AQUÍ ***********************/
			reward = Reward.getReward(rivalTeam.getCurrentPokemon(), myTeam.getCurrentPokemon(), rivalBeforeSwitch,
					myBeforeSwitch, rivalDamage, myDamage);
			System.out.println("******************Reward: " + reward + "**************");
		}
		sc.close();
	}

	private static void changePokemon(Team team) {
		System.out.println("Current Pokemon: " + team.getCurrentPokemon());
		System.out.println("Choose a Pokemon: ");
		for (int j = 0; j < team.getTeam().length; j++) {
			if (!team.getTeam()[j].isFainted())
				System.out.println(j + ". " + team.getTeam()[j]);
		}
		int newPokemon = Integer.parseInt(sc.nextLine());
		team.setCurrentPokemon(newPokemon);
		System.out.println("Your choice: " + team.getTeam()[newPokemon]);
	}

	private static int attack(Pokemon attacker, Pokemon attacked, boolean special) {
		int hpBefore = attacked.getHp();

		if (special) {
			System.out.println(attacker + " used special attack");
			attacked.receiveAttack(attacker.getTypeAttack());
			System.out.println(attacked + " HP = " + attacked.getHp());
		} else {
			System.out.println(attacker + " used neutral attack");
			attacked.receiveAttack(attacker.getNeutralAttack());
			System.out.println(attacked + " HP = " + attacked.getHp());
		}
		int hpAfter = attacked.getHp();

		return hpBefore - hpAfter;

	}

	private static Pokemon[] generateRandomTeam() {
		ArrayList<String> list = new ArrayList<>();
		AvailablePokemon ap;
		Pokemon[] team = new Pokemon[3];

		for (int i = 0; i < 3; i++) {
			ap = AvailablePokemon.randomPokemon();
			if (!list.contains(ap.getType())) {
				list.add(ap.getType());
				team[i] = new Pokemon(ap);
			} else
				i--;
		}
		return team;
	}

}
