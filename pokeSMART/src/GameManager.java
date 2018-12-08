import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int reward1 = 0;
		int reward2 = 0;
		boolean end = false;

//		Team rivalTeam = new Team(generateRandomTeam());
//		Team team2 = new Team(generateRandomTeam());

		Pokemon[] pokemonteam2 = new Pokemon[3];
		pokemonteam2[0] = new Pokemon(AvailablePokemon.Poliwag);
		pokemonteam2[1] = new Pokemon(AvailablePokemon.Poochyena);
		pokemonteam2[2] = new Pokemon(AvailablePokemon.Charmander);
		Team teamPlayer1 = new Team(pokemonteam2);

		Pokemon[] pokemonteam1 = new Pokemon[3];
		pokemonteam1[0] = new Pokemon(AvailablePokemon.Squirtle);
		pokemonteam1[1] = new Pokemon(AvailablePokemon.Umbreon);
		pokemonteam1[2] = new Pokemon(AvailablePokemon.Vulpix);
		Team teamPlayer2 = new Team(pokemonteam1);

		Player player1 = new Player(teamPlayer1);
		Player player2 = new Player(teamPlayer2);

		System.out.println("Team 1: " + teamPlayer2.toString());
		System.out.println("Team 2: " + teamPlayer1.toString());

		System.out.println("Number of players[1 or 2]: ");
		int players = Integer.parseInt(sc.nextLine());

		int count = 0;
		int episodes = 10;

		while (count < episodes) {
			int count1 = 0;
			int count2 = 0;
			for (int i = 0; i < teamPlayer2.getTeam().length; i++) {
				if (teamPlayer2.getTeam()[i].isFainted())
					count1++;
			}
			for (int i = 0; i < teamPlayer1.getTeam().length; i++) {
				if (teamPlayer1.getTeam()[i].isFainted())
					count2++;
			}
			if (count1 == 3 && count2 == 3) {
				System.out.println("Game over");
				end = true;
			}
			if (count1 == 3) {
				System.out.println("Player 1 won the battle.");
				end = true;
			}
			if (count2 == 3) {
				System.out.println("Player 2 won the battle.");
				end = true;
			}
			if (end) {
				count++;
				if (count == episodes)
					break;
				pokemonteam2 = new Pokemon[3];
				pokemonteam2[0] = new Pokemon(AvailablePokemon.Poliwag);
				pokemonteam2[1] = new Pokemon(AvailablePokemon.Poochyena);
				pokemonteam2[2] = new Pokemon(AvailablePokemon.Charmander);
				teamPlayer1 = new Team(pokemonteam2);
				player1.setMyTeam(teamPlayer1);

				pokemonteam1 = new Pokemon[3];
				pokemonteam1[0] = new Pokemon(AvailablePokemon.Squirtle);
				pokemonteam1[1] = new Pokemon(AvailablePokemon.Umbreon);
				pokemonteam1[2] = new Pokemon(AvailablePokemon.Vulpix);
				teamPlayer2 = new Team(pokemonteam1);
				player2.setMyTeam(teamPlayer2);
				end = false;
			}
			Pokemon rivalBeforeSwitch = teamPlayer2.getCurrentPokemon();
			Pokemon myBeforeSwitch = teamPlayer1.getCurrentPokemon();

			int movement[] = new int[2];
			System.out.println("*********** Battle: " + teamPlayer2.getCurrentPokemon().getName() + "["
					+ teamPlayer2.getCurrentPokemon().getHp() + "]" + " vs " + teamPlayer1.getCurrentPokemon().getName()
					+ "[" + teamPlayer1.getCurrentPokemon().getHp() + "]" + " **********");
//			if (rivalTeam.getCurrentPokemon().isFainted()) {
//				changePokemon(rivalTeam);
//			}
//			System.out.println("[" + rivalTeam.getCurrentPokemon() + "] Choose a movement: ");
//			System.out.println("1. Switch");
//			System.out.println("2. Neutral Attack");
//			System.out.println("3. Type Attack");
//
//			movement[0] = Integer.parseInt(sc.nextLine());
//
//			if (movement[0] == 1) {
//				changePokemon(rivalTeam);
//			}

			switch (players) {
			case 2:
				if (teamPlayer1.getCurrentPokemon().isFainted()) {
					changePokemon(teamPlayer1);
				}

				System.out.println("[" + teamPlayer1.getCurrentPokemon() + "] Choose a movement: ");
				System.out.println("1. Switch");
				System.out.println("2. Neutral Attack");
				System.out.println("3. Type Attack");

				movement[1] = Integer.parseInt(sc.nextLine());

				if (movement[1] == 1) {
					changePokemon(teamPlayer1);
				}
				break;

			case 1:

				if (teamPlayer2.getCurrentPokemon().isFainted()) {
					teamPlayer2.setCurrentPokemon(player2.chooseMovement(true, null, 0));
					System.out.println("Player AI 2 switch to " + teamPlayer2.getCurrentPokemon());
				}
				State s = new State(teamPlayer2.getCurrentPokemon(), teamPlayer2.getBackup1(), teamPlayer2.getBackup2(),
						teamPlayer1.getCurrentPokemon(), teamPlayer1.getBackup1(), teamPlayer1.getBackup2());
				movement[0] = player2.chooseMovement(false, s, reward2);
				if (movement[0] == 10) {
					teamPlayer2.setCurrentPokemon(teamPlayer2.backup1);
					System.out.println("Player AI 2 switch to " + teamPlayer2.getCurrentPokemon());
				} else if (movement[0] == 11) {
					teamPlayer2.setCurrentPokemon(teamPlayer2.backup2);
					System.out.println("Player AI 2 switch to " + teamPlayer2.getCurrentPokemon());
				}
				if (movement[0] == 12) {
					if (teamPlayer2.getBackup1().isFainted())
						teamPlayer2.setCurrentPokemon(teamPlayer2.backup2);
					else
						teamPlayer2.setCurrentPokemon(teamPlayer2.backup1);
				}

				if (teamPlayer1.getCurrentPokemon().isFainted()) {
					teamPlayer1.setCurrentPokemon(player1.chooseMovement(true, null, 0));
					System.out.println("Player AI switch to " + teamPlayer1.getCurrentPokemon());
				}
				State s1 = new State(teamPlayer1.getCurrentPokemon(), teamPlayer1.getBackup1(),
						teamPlayer1.getBackup2(), teamPlayer2.getCurrentPokemon(), teamPlayer2.getBackup1(),
						teamPlayer2.getBackup2());
				movement[1] = player1.chooseMovement(false, s1, reward1);
				if (movement[1] == 10) {
					teamPlayer1.setCurrentPokemon(teamPlayer1.backup1);
					System.out.println("Player AI switch to " + teamPlayer1.getCurrentPokemon());
				} else if (movement[1] == 11) {
					teamPlayer1.setCurrentPokemon(teamPlayer1.backup2);
					System.out.println("Player AI switch to " + teamPlayer1.getCurrentPokemon());
				}
				if (movement[1] == 12) {
					if (teamPlayer1.getBackup1().isFainted())
						teamPlayer1.setCurrentPokemon(teamPlayer1.backup2);
					else
						teamPlayer1.setCurrentPokemon(teamPlayer1.backup1);
				}
				break;
			}
			int damagePlayer1 = 0;
			int damagePlayer2 = 0;
			for (int i = 0; i < movement.length; i++) {
				switch (movement[i]) {
				case 2:
					if (i == 0) {
						damagePlayer1 = attack(teamPlayer2.getCurrentPokemon(), teamPlayer1.getCurrentPokemon(), false);
					} else if (i == 1) {
						damagePlayer2 = attack(teamPlayer1.getCurrentPokemon(), teamPlayer2.getCurrentPokemon(), false);
					}
					break;

				case 3:
					if (i == 0) {
						damagePlayer1 = attack(teamPlayer2.getCurrentPokemon(), teamPlayer1.getCurrentPokemon(), true);
					} else if (i == 1) {
						damagePlayer2 = attack(teamPlayer1.getCurrentPokemon(), teamPlayer2.getCurrentPokemon(), true);
					}
					break;
				case 4:
					end = true;
					break;
				}
			}

			/******************* CALCULAR REWARD AQU� ***********************/
			reward1 = Reward.getReward(teamPlayer2.getCurrentPokemon(), teamPlayer1.getCurrentPokemon(),
					rivalBeforeSwitch, myBeforeSwitch, damagePlayer2, damagePlayer1);
			reward2 = Reward.getReward(teamPlayer1.getCurrentPokemon(), teamPlayer2.getCurrentPokemon(), myBeforeSwitch,
					rivalBeforeSwitch, damagePlayer1, damagePlayer2);
			System.out.println("******************Reward 1: " + reward1 + "**************");
			System.out.println("******************Reward 1: " + reward2 + "**************");
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
