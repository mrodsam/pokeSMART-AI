import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		boolean end = false;

		Team team1 = new Team(generateRandomTeam());
		Team team2 = new Team(generateRandomTeam());
		Player randomPlayer = new Player(team2);

		System.out.println("Team 1: " + team1.toString());
		System.out.println("Team 2: " + team2.toString());

		System.out.println("Number of players[1 or 2]: ");
		int players = Integer.parseInt(sc.nextLine());

		while (!end) {
			int count1 = 0;
			int count2 = 0;
			for (int i = 0; i < team1.getTeam().length; i++) {
				if (team1.getTeam()[i].isFainted())
					count1++;
			}
			for (int i = 0; i < team2.getTeam().length; i++) {
				if (team2.getTeam()[i].isFainted())
					count2++;
			}
			if (count1 == 3 && count2 == 3) {
				System.out.println("Game over");
				end = true;
				break;
			}
			if (count1 == 3) {
				System.out.println("Player 2 won the battle.");
				end = true;
				break;
			}
			if (count2 == 3) {
				System.out.println("Player 1 won the battle.");
				end = true;
				break;
			}

			int movement[] = new int[2];
			System.out.println("*********** Battle: " + team1.getCurrentPokemon().getName() + "["
					+ team1.getCurrentPokemon().getHp() + "]" + " vs " + team2.getCurrentPokemon().getName() + "["
					+ team2.getCurrentPokemon().getHp() + "]" + " **********");
			if (team1.getCurrentPokemon().isFainted()) {
				changePokemon(team1);
			}
			System.out.println("[" + team1.getCurrentPokemon() + "] Choose a movement: ");
			System.out.println("1. Switch");
			System.out.println("2. Neutral Attack");
			System.out.println("3. Special Attack");

			movement[0] = Integer.parseInt(sc.nextLine());

			if (movement[0] == 1) {
				changePokemon(team1);
			}

			switch (players) {
			case 2:
				if (team2.getCurrentPokemon().isFainted()) {
					changePokemon(team2);
				}

				System.out.println("[" + team2.getCurrentPokemon() + "] Choose a movement: ");
				System.out.println("1. Switch");
				System.out.println("2. Neutral Attack");
				System.out.println("3. Special Attack");

				movement[1] = Integer.parseInt(sc.nextLine());

				if (movement[1] == 1) {
					changePokemon(team2);
				}
				break;

			case 1:
				if (team2.getCurrentPokemon().isFainted()) {
					team2.setCurrentPokemon(team2.getTeam()[randomPlayer.chooseMovement(true)]);
					System.out.println("Player 2 switch to " + team2.getCurrentPokemon());
				}
				movement[1] = randomPlayer.chooseMovement(false);
				if (movement[1] == 1) {
					team2.setCurrentPokemon(team2.getTeam()[randomPlayer.chooseMovement(true)]);
					System.out.println("Player 2 switch to " + team2.getCurrentPokemon());
				}
				break;
			}

			for (int i = 0; i < movement.length; i++) {
				switch (movement[i]) {
				case 2:
					if (i == 0) {
						attack(team1.getCurrentPokemon(), team2.getCurrentPokemon(), false);
					} else if (i == 1) {
						attack(team2.getCurrentPokemon(), team1.getCurrentPokemon(), false);
					}
					break;

				case 3:
					if (i == 0) {
						attack(team1.getCurrentPokemon(), team2.getCurrentPokemon(), true);
					} else if (i == 1) {
						attack(team2.getCurrentPokemon(), team1.getCurrentPokemon(), true);
					}
					break;
				case 4:
					end = true;
					break;
				}
			}
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
		team.setCurrentPokemon(team.getTeam()[newPokemon]);
		System.out.println("Your choice: " + team.getTeam()[newPokemon]);
	}

	private static void attack(Pokemon attacker, Pokemon attacked, boolean special) {
		if (special) {
			System.out.println(attacker + " used special attack");
			attacked.receiveAttack(attacker.getSpecialAttack());
			System.out.println(attacked + " HP = " + attacked.getHp());
		} else {
			System.out.println(attacker + " used neutral attack");
			attacked.receiveAttack(attacker.getNeutralAttack());
			System.out.println(attacked + " HP = " + attacked.getHp());
		}

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
